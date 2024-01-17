package com.yash.messiahwamr

//import android.app.Fragment
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
import android.widget.Toast
import com.bumptech.glide.Glide
//import com.yash.messiah.R
import java.io.File
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Picture.newInstance] factory method to
 * create an instance of this fragment.
 */
class Picture : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var download: ImageView
    lateinit var share: ImageView
    lateinit var nparticularimage: ImageView

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
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        nparticularimage=view.findViewById(R.id.particularimage)
        share=view.findViewById(R.id.share)
        download=view.findViewById(R.id.download)

        var data=arguments
//        var intent=getIntent()

        var destpath=data?.getString("DEST_PATH_PICTURE")

        var file=data?.getString("FILE_PICTURE")
        var uri = data?.getString("URI_PICTURE")
        var name=data?.getString("FILENAME_PICTURE")


        var uri1= Uri.parse(uri)
        var destpath2= File(destpath)
        var file1= File(file)
        Glide.with(requireContext()).load(uri).into(nparticularimage)
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        share.setOnClickListener({
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri1)
                type = "image/*"
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
            MediaScannerConnection.scanFile(requireContext(), arrayOf((destpath+name).toString()), arrayOf("*/*"),object: MediaScannerConnection.MediaScannerConnectionClient{
                override fun onScanCompleted(path: String?, uri: Uri?) {
                }

                override fun onMediaScannerConnected() {
                }


            })
            Toast.makeText(requireContext(),"Picture saved to GALLERY successfully", Toast.LENGTH_LONG).show()

        })



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Picture.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Picture().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}