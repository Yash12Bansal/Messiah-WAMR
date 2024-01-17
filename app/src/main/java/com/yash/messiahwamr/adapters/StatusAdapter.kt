package com.yash.messiahwamr.adapters

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yash.messiahwamr.EachPicture
import com.yash.messiahwamr.EachVideo
import com.yash.messiahwamr.R
import com.yash.messiahwamr.models.ModelClass
import java.io.File


class StatusAdapter(var context: Context, var fileslist: ArrayList<ModelClass>?): RecyclerView.Adapter<StatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusAdapter.ViewHolder {
        var view= LayoutInflater.from(context).inflate(R.layout.item_layout,null,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatusAdapter.ViewHolder, position: Int) {
        var modelClass=fileslist?.get(position)
        if(modelClass?.filename.toString().endsWith(".mp4")){
            holder.play.visibility= View.VISIBLE
        }
        else{
            holder.play.visibility= View.INVISIBLE
        }

//        println("helfldfdlkfjdlkfjdklfjdkfjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj")
//        println(filelist)
//        println()

        Glide.with(context).load(modelClass?.uri).into(holder.mainstatus)
        holder.mainstatus.setOnClickListener({
            if(modelClass?.filename.toString().endsWith(".mp4")){
                var path=fileslist?.get(position)?.path
                var destination=Environment.getExternalStorageDirectory().absolutePath+"/WhatsApp Messiah/Downloaded Statuses/"
//                var bundle=Bundle()
//                bundle.putString("DEST_PATH_VIDEO",destination)
//                bundle.putString("FILE_VIDEO",path)
//                bundle.putString("FILENAME_VIDEO",modelClass?.filename)
//                bundle.putString("URI_VIDEO",modelClass?.uri.toString())
//                it.findNavController().navigate(R.id.video,bundle)
                var intent= Intent(context, EachVideo::class.java).apply{
                    putExtra("DEST_PATH_VIDEO",destination)
                    putExtra("FILE_VIDEO",path)
                    putExtra("FILENAME_VIDEO",modelClass?.filename)
                    putExtra("URI_VIDEO",modelClass?.uri.toString())
                    putExtra("MIME_TYPE",modelClass?.mimeType)

                }
                context.startActivity(intent)
            }
            else{
                var path=fileslist?.get(position)?.path
                var destination=Environment.getExternalStorageDirectory().absolutePath+"/WhatsApp Messiah/Downloaded Statuses/"

//                var bundle=Bundle()
//                bundle.putString("DEST_PATH_VIDEO",destination)
//                bundle.putString("FILE_VIDEO",path)
//                bundle.putString("FILENAME_VIDEO",modelClass?.filename)
//                bundle.putString("URI_VIDEO",modelClass?.uri.toString())
//                it.findNavController().navigate(R.id.picture,bundle)


                var intent= Intent(context, EachPicture ::class.java).apply{
                    putExtra("DEST_PATH_PICTURE",destination)
                    putExtra("FILE_PICTURE",path)
                    putExtra("FILENAME_PICTURE",modelClass?.filename)
                    putExtra("URI_PICTURE",modelClass?.uri.toString())
                    putExtra("MIME_TYPE",modelClass?.mimeType)

                }

                context.startActivity(intent)


            }
        })
    }

    override fun getItemCount(): Int {
        return fileslist?.size?:0
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mainstatus=itemView.findViewById<ImageView>(R.id.thumbnailofstatus)
        var play=itemView.findViewById<ImageView>(R.id.play)



    }

}