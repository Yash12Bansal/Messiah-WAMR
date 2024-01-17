package com.yash.messiahwamr.adapters

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yash.messiahwamr.EachDeletedPhoto
import com.yash.messiahwamr.EachDeletedVideo
import com.yash.messiahwamr.R
//import com.yash.messiah.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PhotosFragmentAdapter(var context:Context): RecyclerView.Adapter<PhotosFragmentAdapter.CustomViewHolder>() {

    var photoList=emptyList<File>()
    var deletedPhotoList=emptyList<File>()

    inner class CustomViewHolder(v: View):RecyclerView.ViewHolder(v){
        var image=v.findViewById<ImageView>(R.id.photo_video)
        var play=v.findViewById<ImageView>(R.id.play_button)
//        var name=v.findViewById<TextView>(R.id.name)
        var date=v.findViewById<TextView>(R.id.date)
        var deletedText=v.findViewById<TextView>(R.id.deleted_text)
        var pC=v.findViewById<CardView>(R.id.photo_card)
        var re=v.findViewById<TextView>(R.id.recovered)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var layout= LayoutInflater.from(parent.context).inflate(R.layout.photo_item,parent,false)
        return CustomViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

//        with(holder) {
//            image.setImageURI(photoList[position].toUri())
//
//        }

//        holder.name.text=photoList[position].name
        val lastModDate = Date(photoList[position].lastModified())
//            var da = LocalDate.parse(lastModDate)

        var pattern:String?=null
        if (Build.VERSION.SDK_INT >= 24){
            pattern = "MMMM d,yyyy"

        }
        else{
            pattern = "yyyy-MM-dd"

        }

        val simpleDateFormat = SimpleDateFormat(pattern)
        val datee: String = simpleDateFormat.format(lastModDate)
//            println(date)
        holder.date.setText(datee)



        var ff=false
        for(i in 0..deletedPhotoList.size-1){
            if(deletedPhotoList[i].name==photoList[position].name){
                ff=true
                break
            }

        }

        if(ff){
            println("QIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII")
            holder.deletedText.visibility=View.VISIBLE
            holder.image.alpha= 0.4F
            var x=ContextCompat.getColor(context, R.color.lred)
            var xx=ContextCompat.getColor(context, R.color.white)

            holder.date.setTextColor(xx)
            holder.re.visibility=View.VISIBLE
            holder.pC.setCardBackgroundColor(x)
        }
        else{
            holder.re.visibility=View.INVISIBLE
            var xx=ContextCompat.getColor(context, R.color.black)

            holder.date.setTextColor(xx)

            println("AIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII")
            holder.image.alpha= 1F
            var x=ContextCompat.getColor(context, R.color.white)

            holder.pC.setCardBackgroundColor(x)

            holder.deletedText.visibility=View.INVISIBLE
        }
        if(photoList[position].toUri().toString().endsWith(".mp4")){
            holder.play.visibility= View.VISIBLE
        }
        else{
            holder.play.visibility= View.INVISIBLE
        }

        Glide.with(context).load(photoList[position]?.toUri()).into(holder.image)
        holder.image.setOnClickListener({
            if (photoList[position].toUri().toString().endsWith(".mp4")) {

                holder.play.visibility=View.VISIBLE
                var path = photoList?.get(position)?.path
                var destination =
                    context?.getExternalFilesDir(null)?.absolutePath+"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}"
                //                var bundle=Bundle()
//                bundle.putString("DEST_PATH_VIDEO",destination)
//                bundle.putString("FILE_VIDEO",path)
//                bundle.putString("FILENAME_VIDEO",modelClass?.filename)
//                bundle.putString("URI_VIDEO",modelClass?.uri.toString())
//                it.findNavController().navigate(R.id.video,bundle)
                var intent = Intent(context, EachDeletedVideo::class.java).apply {
                    putExtra("DEST_PATH_VIDEO", destination)
                    putExtra("FILE_VIDEO", path)
                    putExtra("FILENAME_VIDEO",photoList[position]?.name)
                    putExtra("URI_VIDEO", photoList[position].toUri().toString())

                }

                context.startActivity(intent)



            }

            else{

                holder.play.visibility=View.INVISIBLE
                var path = photoList?.get(position)?.path
                var destination =
                    context?.getExternalFilesDir(null)?.absolutePath+"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}"
//                var bundle=Bundle()
//                bundle.putString("DEST_PATH_VIDEO",destination)
//                bundle.putString("FILE_VIDEO",path)
//                bundle.putString("FILENAME_VIDEO",modelClass?.filename)
//                bundle.putString("URI_VIDEO",modelClass?.uri.toString())
//                it.findNavController().navigate(R.id.video,bundle)
                var intent = Intent(context, EachDeletedPhoto::class.java).apply {
                    putExtra("DEST_PATH_PICTURE", destination)
                    putExtra("FILE_PICTURE", path)
                    putExtra("FILENAME_PICTURE",photoList[position]?.name)
                    putExtra("URI_PICTURE", photoList[position].toUri().toString())

                }

                context.startActivity(intent)
            }
        })
    }


    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setData2(data: List<File>){
        this.deletedPhotoList=data
        notifyDataSetChanged()
    }
    fun setData(data:List<File>){
        this.photoList=data
        notifyDataSetChanged()
    }
}