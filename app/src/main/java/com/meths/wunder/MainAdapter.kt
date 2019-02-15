package com.meths.wunder

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meths.wunder.Model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_row.view.*

class MainAdapter(val userList: ArrayList<User>): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.user_row, parent, false)
        return  CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val user = userList[position]

        val font = Typeface.createFromAsset(holder.view.context!!.assets, "Gotham-Medium-Regular.ttf")
        holder.view.userTitle?.setTypeface(font, Typeface.NORMAL)
        holder.view.userTitle?.text = user.name.first.capitalize() + ", " + user.dob.age

        Picasso.get().load(user.picture.medium).into(holder.itemView.circleImageView)


        holder.view.userDetail.setOnClickListener {
            userDetailUser = user
            val intent = Intent((holder.view.context as AppCompatActivity).applicationContext, UserDetail::class.java)
            (holder.view.context as AppCompatActivity).startActivity(intent)
        }

    }

}
class  CustomViewHolder(val view : View): RecyclerView.ViewHolder(view){

}