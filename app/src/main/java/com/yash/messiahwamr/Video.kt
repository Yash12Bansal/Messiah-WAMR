package com.yash.messiahwamr

import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
//import com.yash.messiah.R
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Video.newInstance] factory method to
 * create an instance of this fragment.
 */
class Video : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var download: ImageView
    //    lateinit var mychat: ImageView
    lateinit var share: ImageView
    lateinit var nparticularvideo: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        nparticularvideo=view.findViewById(R.id.particularvideo)
        share=view.findViewById(R.id.share)
//        mychat=findViewById(R.id.mychatapp)
        download=view.findViewById(R.id.download)
        var data=arguments
//        var intent=getIntent()

        var destpath=data?.getString("DEST_PATH_PICTURE")

        var file=data?.getString("FILE_PICTURE")
        var uri = data?.getString("URI_PICTURE")
        var name=data?.getString("FILENAME_PICTURE")

        var destpath2= File(destpath)
        var file1= File(file)

        var mediaController= MediaController(requireContext())
        mediaController.setAnchorView(nparticularvideo)
        var uri1= Uri.parse(uri)
        nparticularvideo.setMediaController(mediaController)
        nparticularvideo.setVideoURI(uri1)
        nparticularvideo.requestFocus()
        nparticularvideo.start()
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        share.setOnClickListener({
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri1)
                type = "video/mp4"
            }
            startActivity(Intent.createChooser(shareIntent, null))

        })
        download.setOnClickListener({
            try{
                org.apache.commons.io.FileUtils.copyFileToDirectory(file1,destpath2)


            }
            catch (e: Exception){
                e.printStackTrace()
            }
            MediaScannerConnection.scanFile(requireContext(), arrayOf(destpath+name), arrayOf("*/*"),object: MediaScannerConnection.MediaScannerConnectionClient{
                override fun onScanCompleted(path: String?, uri: Uri?) {
                }

                override fun onMediaScannerConnected() {
                }


            })

            Toast.makeText(requireActivity(),"Video saved to GALLERY successfully", Toast.LENGTH_LONG).show()
        })



    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Video.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Video().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}