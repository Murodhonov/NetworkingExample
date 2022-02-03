package uz.umarxon.networking

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import uz.umarxon.networking.Utils.NetworkHelper
import uz.umarxon.networking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var networkHelper: NetworkHelper
    lateinit var requestQueue:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)


        loadObject()
    }

    fun loadObject(){
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, "http://ip.jsontest.com", null,
                object : Response.Listener<JSONObject> {
                    override fun onResponse(response: JSONObject?) {
                        binding.tv.text = response?.getString("ip")
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {
                        Toast.makeText(this@MainActivity,
                            "Error \n ${error?.message}",
                            Toast.LENGTH_SHORT).show()
                    }
                })

        requestQueue.add(jsonObjectRequest)
    }

    fun loadArrayList(){
        var jsonArrayRequest = JsonArrayRequest(Request.Method.GET,"http://api.github.com/users",null,
        object : Response.Listener<JSONArray>{
            override fun onResponse(response: JSONArray?) {
                binding.tv.text = response.toString()
            }
        },
        object : Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                Toast.makeText(this@MainActivity, "Error \n ${error?.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        requestQueue.add(jsonArrayRequest)
    }


}