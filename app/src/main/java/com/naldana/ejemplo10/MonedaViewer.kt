package com.naldana.ejemplo10

import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import com.naldana.ejemplo10.models.Moneda
import com.naldana.ejemplo10.utils.NetworkUtils
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class MonedaViewer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewer_element_pokemon)

        val uri:String = this.intent.extras.getString("CLAVIER")
        setSupportActionBar(toolbarviewer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        collapsingtoolbarviewer.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingtoolbarviewer.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        FetchPokemonTask().execute(uri)
    }

    fun init(moneda: Moneda){
        Picasso.with(this)
            .load(moneda.sprite)
            .resize((this.resources.displayMetrics.widthPixels/this.resources.displayMetrics.density).toInt(),256)
            .centerCrop()
            .error(R.drawable.ic_pokemon_go)
            .into(app_bar_image_viewer)
        collapsingtoolbarviewer.title = pokemon.name
        weight.text = pokemon.weight
        height.text = pokemon.height
        fstType.text = pokemon.fsttype
        sndType.text = pokemon.sndtype
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item!!.itemId){
            android.R.id.home -> {onBackPressed();true}
            else -> super.onOptionsItemSelected(item)
        }
    }

    private inner class FetchPokemonTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg query: String): String {

            if (query.isNullOrEmpty()) return ""

            val url = query[0]
            val pokeAPI = Uri.parse(url).buildUpon().build()
            val finalurl = try {
                URL(pokeAPI.toString())
            } catch (e: MalformedURLException){
                URL("")
            }

            return try {
                NetworkUtils().getResponseFromHttpUrl(finalurl)
            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }

        }

        override fun onPostExecute(pokemonInfo: String) {
            val pokemon = if (!pokemonInfo.isEmpty()) {
                val root = JSONObject(pokemonInfo)
                val sprites = root.getString("sprites")
                val types = root.getJSONArray("types")
                val fsttype = JSONObject(types[0].toString()).getString("type")
                val sndtype = try { JSONObject(types[1].toString()).getString("type") }catch (e: JSONException){ "" }

                Pokemon(root.getInt("id"),
                    root.getString("name").capitalize(),
                    JSONObject(fsttype).getString("name").capitalize(),
                    if(sndtype.isEmpty()) " " else JSONObject(sndtype).getString("name").capitalize(),
                    root.getString("weight"), root.getString("height"), root.getString("location_area_encounters"),
                    JSONObject(sprites).getString("front_default"))

            } else {
                Pokemon(0, R.string.n_a_value.toString(), R.string.n_a_value.toString(), R.string.n_a_value.toString(),R.string.n_a_value.toString(), R.string.n_a_value.toString(), R.string.n_a_value.toString(), R.string.n_a_value.toString())
            }
            init(pokemon)
        }
    }
}