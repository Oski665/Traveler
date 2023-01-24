package com.example.traveler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountriesAdapter(private val countriesList: ArrayList<CountriesData>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvCountry)
        val imageView: ImageView = itemView.findViewById(R.id.ivCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageName = imageNames[position]
        holder.nameTextView.text = imageName.name
        holder.imageView.setImageBitmap(imageName.image)
    }

    override fun getItemCount() = imageNames.size

    val imageNames = listOf(
        ImageName("Obraz 1", BitmapFactory.decodeResource(resources, R.drawable.image1)),
        ImageName("Obraz 2", BitmapFactory.decodeResource(resources, R.drawable.image2)),
        ImageName("Obraz 3", BitmapFactory.decodeResource(resources, R.drawable.image3))
    )
    val adapter = ImageNameAdapter(imageNames)
    recyclerView.adapter = adapter
}

