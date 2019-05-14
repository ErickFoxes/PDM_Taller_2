package com.naldana.ejemplo10

import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import com.naldana.ejemplo10.models.Moneda
import com.naldana.ejemplo10.utils.NetworkUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.viewer_element_moneda.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class MonedaViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewer_element_moneda)

        //val uri: String = this.intent.extras.getString("CLAVIER")
        setSupportActionBar(toolbarviewer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        collapsingtoolbarviewer.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingtoolbarviewer.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)
    }

    fun init(moneda: Moneda) {
        Picasso.with(this)
            .load(moneda.image)
            .resize((this.resources.displayMetrics.widthPixels / this.resources.displayMetrics.density).toInt(), 256)
            .centerCrop()
            .error(R.drawable.ic_pokemon_go)
            .into(app_bar_image_viewer)
        collapsingtoolbarviewer.title = moneda.name
        value.text = moneda.value.toString()
        value_us.text = moneda.value_us.toString()
        year.text = moneda.year.toString()
        review.text = moneda.review

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed();true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private inner class FetchMonedaTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg query: String): String {

            if (query.isNullOrEmpty()) return ""

            val url = query[0]
            val monedaAPI = Uri.parse(url).buildUpon().build()
            val finalurl = try {
                URL(monedaAPI.toString())
            } catch (e: MalformedURLException) {
                URL("")
            }

            return try {
                NetworkUtils.NetworkUtils().getResponseFromHttpUrl(finalurl)
            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }
        }


        override fun onPostExecute(monedaInfo: String) {
            val moneda = if (!monedaInfo.isEmpty()) {
                val root = JSONObject(monedaInfo)
                val results = root.getJSONArray("post")
                val result = JSONObject(results[0].toString())

                Moneda(
                    result.getString("id"),
                    result.getString("name").capitalize(),
                    result.getString("country"),
                    result.getInt("value"),
                    result.getInt("value_us"),
                    result.getInt("year"),
                    result.getString("review"),
                    result.getBoolean("isAvailable"),
                    result.getString("image")

                )
            } else {
                Moneda(
                    R.string.n_a_value.toString(),
                    R.string.n_a_value.toString(),
                    R.string.n_a_value.toString(),
                    R.integer.int_cero,
                    R.integer.int_cero,
                    R.integer.int_cero,
                    R.string.n_a_value.toString(),
                    false,
                    R.string.n_a_value.toString()
                )
            }
            init(moneda)
        }


    }

}

