package com.meths.wunder

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_main.*
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.GsonBuilder
import com.meths.wunder.Model.User
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    val url = "https://wunder-provider.herokuapp.com/"
    val userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFont()
        listenSocket()

        recyclerView_main.layoutManager = LinearLayoutManager(this)
    }

    fun listenSocket() {
        val socket = IO.socket("https://wunder-provider.herokuapp.com/")
        socket.connect()
            .on(Socket.EVENT_CONNECT, { println("connected") })
            .on("userList", onUserList)
            .on(Socket.EVENT_DISCONNECT, { println("disconnected") })
    }

    private val onUserList = Emitter.Listener { args ->
        this.runOnUiThread(Runnable {
            val data = args[0] as JSONObject
            val results:String

            try {
                results = data.getString("results")

                val gson = GsonBuilder().create()
                val userFeed = gson.fromJson(results, Array<User>::class.java)

                userFeed.forEach {
                    userList.add(it)

                    if (userList.size > 10) {
                        userList.removeAt(0)
                    }


                }

                if (this != null) {
                    this!!.runOnUiThread {
                        if (recyclerView_main != null) {
                            recyclerView_main.adapter = MainAdapter(userList)
                        }
                    }
                }
            } catch (e: JSONException) {
                return@Runnable
            }
        })
    }

    private fun setupFont() {
        val gbr = Typeface.createFromAsset(this.assets, "Gotham-Book-Regular.ttf")
        textViewProfile.setTypeface(gbr, Typeface.NORMAL)
    }

}
