package com.example.randommemes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var memeImageURL = ""
    var memeNameURL = ""
    var memeIdURL = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMemeImage()
        getMemeName()
        getMemeId()
        Log.d("memeImageURL", "meme image URL set")

        val button = findViewById<Button>(R.id.getMemeButton)
        val imageView = findViewById<ImageView>(R.id.memeImage)
        val textView = findViewById<TextView>(R.id.memeName)
        val textViewId = findViewById<TextView>(R.id.memeId)
        Log.d("create", "after define buttons")
        getData(button, imageView, textView, textViewId)
        Log.d("create", "after get next Image")
    }

    private fun getData(button: Button, imageView: ImageView, textView: TextView, textViewId: TextView) {
        button.setOnClickListener {
            getMemeImage()
            getMemeName()
            getMemeId()

            Glide.with(this@MainActivity)
                .load(memeImageURL)
                .fitCenter()
                .into(imageView)
            textView.text = memeNameURL
            textViewId.text=memeIdURL

        }
    }

    private fun getMemeImage(){
        val client = AsyncHttpClient()
        client["https://api.imgflip.com/get_memes", object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("Meme Error", response)
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("Meme", "response successful$json")

                val data = json.jsonObject.getJSONObject("data")
                val memes = data.getJSONArray("memes")
                val single = memes.getJSONObject(Random.nextInt(0,100))
                Log.d("Meme", "first $single")
                memeImageURL = single.getString("url")
                Log.d("getMeme", "imageURL $memeImageURL")
                }
    }]
}
    private fun getMemeName(){
        val client = AsyncHttpClient()
        client["https://api.imgflip.com/get_memes", object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("Meme Error", response)
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("memeName", "response successful$json")

                val data = json.jsonObject.getJSONObject("data")
                val memes = data.getJSONArray("memes")
                val single = memes.getJSONObject(Random.nextInt(0,100))
                Log.d("memeName", "first $single")
                memeNameURL = single.getString("name")
                Log.d("getMemeName", "memeNameURL $memeNameURL")
            }
        }]
    }
    private fun getMemeId(){
        val client = AsyncHttpClient()
        client["https://api.imgflip.com/get_memes", object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("Meme Error", response)
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("memeName", "response successful$json")

                val data = json.jsonObject.getJSONObject("data")
                val memes = data.getJSONArray("memes")
                val single = memes.getJSONObject(Random.nextInt(0,100))
                Log.d("memeName", "first $single")
                memeIdURL = single.getString("id")
                Log.d("getMemeID", "memeIdURL $memeIdURL")
            }
        }]
    }
}
