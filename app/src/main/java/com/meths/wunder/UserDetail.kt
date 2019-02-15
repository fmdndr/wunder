package com.meths.wunder

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.meths.wunder.Model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_detail.*

var userDetailUser: User? = null

class UserDetail: AppCompatActivity() {
    lateinit var  mapFragment: SupportMapFragment
    lateinit var  googleMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        backMain()
        setupMap()
        setupFont()

    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        Picasso.get().load(userDetailUser!!.picture.medium).into(userDetailimage)
        userFirstlast?.text= userDetailUser!!.name.first.capitalize()+" "+ userDetailUser!!.name.last
        userAgeindex?.text= userDetailUser!!.dob.age.toString()
         val gender = userDetailUser!!.gender
           if(gender=="female"){

               genderView?.setImageResource(R.drawable.woman)
           }else{
               genderView?.setImageResource(R.drawable.male)

           }


        println(userDetailUser!!.name.first)

    }

    fun backMain(){
        fmd.setOnClickListener{
            finish()
        }
    }

    fun setupMap() {
        val lat = userDetailUser!!.location.coordinates.latitude
        val long = userDetailUser!!.location.coordinates.longtitude
        val location = LatLng(lat,long)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync( {
            googleMap = it
            googleMap.addMarker(MarkerOptions().position(location).title("User Location"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
        })
    }

    private fun setupFont() {
        val fontBook = Typeface.createFromAsset(this.assets, "Gotham-Book-Regular.ttf")
        val fontMedium = Typeface.createFromAsset(this.assets,"Gotham-Medium-Regular.ttf")

        profileHeader.setTypeface(fontBook, Typeface.NORMAL)
        userFirstlast.setTypeface(fontMedium, Typeface.BOLD)
        userGender.setTypeface(fontMedium, Typeface.NORMAL)
        userAge.setTypeface(fontMedium, Typeface.NORMAL)
        userAgeindex.setTypeface(fontMedium,Typeface.NORMAL)


    }
}
