package com.naldana.ejemplo10

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naldana.ejemplo10.models.Moneda
import kotlinx.android.synthetic.main.list_element_moneda.view.*

class MonedaAdapter(val items: List<Moneda>, val clickListener: (Moneda) -> Unit) : RecyclerView.Adapter<MonedaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_element_moneda, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)

    //lo que se muestra en el recyclerview
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Moneda, clickListener: (Moneda) -> Unit) = with(itemView) {
            tv_moneda_id.text = item.id.toString()
            tv_moneda_name.text = item.name
            tv_moneda_type.text = item.country
            this.setOnClickListener { clickListener(item) }
        }
    }
}
