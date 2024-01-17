package com.yash.messiahwamr

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.*
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.messiahwamr.adapters.PhotosFragmentAdapter
import com.yash.messiahwamr.models.ModelClass
import com.yash.messiahwamr.viewmodels.PhotoFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home2.*
//import com.yash.messiah.R
import java.io.File
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment2 : Fragment() {
    lateinit var viewModel:PhotoFragmentViewModel
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_home2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(PhotoFragmentViewModel::class.java)
        var linearLayoutManager=LinearLayoutManager(requireActivity())
        linearLayoutManager.reverseLayout=true
        linearLayoutManager.stackFromEnd=true
        recyclerView.apply {
            layoutManager=linearLayoutManager
        }


        var adapter=PhotosFragmentAdapter(requireActivity())
        recyclerView.adapter=adapter

        viewModel._l.observe(viewLifecycleOwner,{data->
            adapter.setData(data)
        })
        viewModel._l2.observe(viewLifecycleOwner,{data->
            adapter.setData2(data)
        })

        swipe.setOnRefreshListener {
            swipe.setRefreshing(true)

            Thread{


//            recyclerView.apply {
//                layoutManager=LinearLayoutManager(requireActivity())
//            }

//            var adapter=PhotosFragmentAdapter(requireActivity())
//            recyclerView.adapter=adapter
//


                recyclerView.postDelayed(object:Runnable{
                    override fun run()
                    {
                        viewModel.reinitalize(application = requireActivity().application!!)
                        swipe.setRefreshing(false)
                    }

                },500)
            }.start()

        }

//        adapter.setData(l.toList())

    }

//    inner class MediaObserving(var context: Context,var treeUri:Uri): FileObserver( treeUri.path ,
//        ALL_EVENTS
//    ) {
//        override fun onEvent(event: Int, path: String?) {
//            println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ12234343434349")
//
//            when (event and FileObserver.ALL_EVENTS) {
//                FileObserver.MODIFY -> {
//                    println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ12234343434349")
//                }
//                FileObserver.CREATE -> {
//                    println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ12234343434349")
//
//                }
//                FileObserver.MOVED_TO->{
//                    var file=File(path)
//                    println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ12234343434349")
//                    lateinit var destinationUri:Uri;
//                    var  values =  ContentValues();
//                    values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM + "/WhatsApp Messiah/Images");
//
//                    destinationUri = requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
//                    var fileD=File(destinationUri.toString())
//
////                    println("THISIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+destF.exists())
////            temp=org.apache.commons.io.FileUtils.
////                    if(!destF.exists()){
////                        var s=destF.mkdirs()
////                        println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
////                    }
////                println("THISIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+destF.exists())
//
//                    org.apache.commons.io.FileUtils.copyFileToDirectory(file,fileD)
//
////                    if(path!=null){
////                        var nu= Uri.parse((path.toString())+"%2FWhatsApp Images")
//////                var uu=DocumentsContract.buildDocumentUriUsingTree(treeUri,DocumentsContract.getDocumentId(treeUri))
//////                println(uu)
////
////                        var myUri= DocumentsContract.buildChildDocumentsUriUsingTree(
////                            path,
////                            DocumentsContract.getTreeDocumentId(treeUri))
////                        val mydirNodes: MutableList<Uri> = LinkedList()
////
////                        val dirNodes: MutableList<Uri> = LinkedList()
////                        var mimeArray:MutableList<String> = mutableListOf()
////                        var nameArray:MutableList<String> = mutableListOf()
////
////                        dirNodes.add(myUri)
////                        while (!dirNodes.isEmpty()) {
////                            myUri = dirNodes.removeAt(0) // get the item from top
//////                    println("GGGGGGGGGGG"+myUri)
//////                    println("GGGGGGGGGGG"+myUri.lastPathSegment)
////
//////                    if(myUri.toString().contains(".Statuses")){
////                            val c: Cursor? = contentResolver.query(
////                                myUri,
////                                arrayOf(
////                                    DocumentsContract.Document.COLUMN_DOCUMENT_ID,
////                                    DocumentsContract.Document.COLUMN_DISPLAY_NAME,
////                                    DocumentsContract.Document.COLUMN_MIME_TYPE
////                                ),
////                                null,
////                                null,
////                                null
////                            )
////                            try {
////                                if (c != null) {
////                                    while (c.moveToNext()) {
////                                        val docId: String = c.getString(0)
////                                        val name: String = c.getString(1)
////                                        val mime: String = c.getString(2)
////
////                                        Log.d("TAG", "docId: $docId, name: $name, mime: $mime")
////                                        var newNode =
////                                            DocumentsContract.buildChildDocumentsUriUsingTree(
////                                                treeUri,
////                                                docId
////                                            )
////                                        if (docId.contains("WhatsApp Images") && !docId.endsWith("WhatsApp Images") && !docId.endsWith(".nomedia") && mime!="vnd.android.document/directory") {
////                                            mydirNodes.add(newNode)
////                                            mimeArray.add(mime)
////                                            nameArray.add(name)
////
////                                        }
////
////                                        dirNodes.add(newNode)
////                                    }
////                                    println(" ")
////
////                                }
////
////
////                            }
////                            finally {
//////                        closeQuietly(c)
////                            }
////                        }
////                        for (i in 0..mydirNodes.size-1) {
////                            var n = mydirNodes[i].pathSegments
////                            var name=nameArray[i]
////                            var path = mydirNodes[i].path
////
////                            var f = ModelClass(path!!, name, mydirNodes[i],mimeArray[i])
////                            saveFile(f.uri,f.filename,f.mimeType!!)
//////                        fileslist.add(f)
////                            println("TT    LL " + i)
////                        }
////                    }
//
//                }
//                FileObserver.DELETE -> {
//                    println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ12234343434349")
//                }
//            }
//        }
//    }
//inner class MediaObserving(var context: Context): FileObserver(File(Environment.getExternalStorageDirectory(),"Android${File.separator}media${File.separator}com.whatsapp${File.separator}WhatsApp${File.separator}Media${File.separator}WhatsApp Images").toString(),
fun observeWhatsAppDocuments() {
    // Register a content observer for the WhatsApp Documents directory
//    val documentsUri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
//    val selection = "${MediaStore.Files.FileColumns.RELATIVE_PATH} like ?"
//    val selectionArgs = arrayOf("%WhatsApp/Media/WhatsApp Images%")
//    var contentObserver: ContentObserver? = null
//
//    contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
//        override fun onChange(selfChange: Boolean, uri: Uri?) {
//            println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")
//        }
//    }

//    this.requireContext()?.contentResolver?.registerContentObserver(documentsUri, true, contentObserver!!)
//    this.context?.contentResolver?.query(documentsUri, null, selection, selectionArgs, null)
}

//    inner class WhatsAppImagesObserver(
//        private var context: Context,
//        private val handler: Handler,
//        private val onPhotoAdded: (Uri) -> Unit
//    ) : ContentObserver(handler) {
//
//        private val contentResolver: ContentResolver = context.contentResolver
//
//        private val whatsappImagesUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//
//        init {
//            contentResolver.registerContentObserver(
//                whatsappImagesUri,
//                true,
//                this
//            )
//        }
//
//        override fun onChange(selfChange: Boolean, uri: Uri?) {
//            super.onChange(selfChange, uri)
//            if (uri != null) {
//                // Check if the change is in the WhatsApp images folder
//                if (isInWhatsAppImagesFolder(uri)) {
//                    onPhotoAdded.invoke(uri)
//                    Log.e("e","PHOTO GOTOTOTOOTOTOTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
//                }
//            }
//        }
//
//        private fun isInWhatsAppImagesFolder(uri: Uri): Boolean {
//            val cursor = contentResolver.query(
//                uri,
//                arrayOf(OpenableColumns.DISPLAY_NAME),
//                null,
//                null,
//                null
//            )
//
//            cursor?.use {
//                val columnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//                if (columnIndex != -1 && it.moveToFirst()) {
//                    val fileName = it.getString(columnIndex)
//                    return fileName.startsWith("IMG_") // Adjust the condition as per your requirement
//                }
//            }
//
//            return false
//        }
//
//        fun unregisterObserver() {
//            contentResolver.unregisterContentObserver(this)
//        }
//    }

    inner class MediaObserving3(var context: Context): FileObserver(File(Environment.getExternalStorageDirectory(),"Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Images").toString(),
    ALL_EVENTS
) {
    var List =emptyList<File>()
    override fun onEvent(p0: Int, p1: String?) {
        if(p0== MOVED_TO){
            println("HEEELELELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLOO1234444")
            var file:File?=null
            if(android.os.Build.VERSION.SDK_INT>=29){
                file=File(Environment.getExternalStorageDirectory(),"Android${File.separator}media${File.separator}com.whatsapp${File.separator}WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}$p1")

            }
            else{
                file=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}$p1")

            }
//
//            var srcF=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}${p1}")
            println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ")
//            println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"+srcF.absoluteFile)
//            var file=File.createTempFile("hel",".jpg")
            var destF=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}")
            println("THISIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+destF.exists())
//            temp=org.apache.commons.io.FileUtils.
            if(!destF.exists()){
                var s=destF.mkdirs()
                println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
            }
//                println("THISIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+destF.exists())

            org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
        }
        if(p0 == DELETE){
            println("sldkdobsererer")
//            var file=File(Environment.getExternalStorageDirectory(),"Android${File.separator}media${File.separator}com.whatsapp${File.separator}WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}$p1")

            try{
                var destF=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")
                if(!destF.exists()){
                    var s=destF.mkdirs()
                    println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
                }


//                var ff=File(destF,p1)
                var file=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}$p1")
                org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
//                    viewModel=ViewModelProvider(this@HomeFragment2).get(PhotoFragmentViewModel::class.java)



//                copyFil
//                temp?.copyTo(destF,false, DEFAULT_BUFFER_SIZE)
            }
            catch(e:Exception){
                println("thdewweww  "+e)
            }
        }
    }
}
    inner class MediaObserving4(var context: Context): FileObserver(File(Environment.getExternalStorageDirectory(),"Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Video").toString(),
        ALL_EVENTS
    ) {
        var List =emptyList<File>()
        override fun onEvent(p0: Int, p1: String?) {
            if(p0== MOVED_TO){
                println("HEEELELELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLOO1234444")
                var file:File?=null
                if(android.os.Build.VERSION.SDK_INT>=29){
                    file=File(Environment.getExternalStorageDirectory(),"Android${File.separator}media${File.separator}com.whatsapp${File.separator}WhatsApp${File.separator}Media${File.separator}WhatsApp Video${File.separator}$p1")

                }
                else{
                    file=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}$p1")

                }
//
//            var srcF=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}${p1}")
                println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ")
//            println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"+srcF.absoluteFile)
//            var file=File.createTempFile("hel",".jpg")
                var destF=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}")
                println("THISIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+destF.exists())
//            temp=org.apache.commons.io.FileUtils.
                if(!destF.exists()){
                    var s=destF.mkdirs()
                    println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
                }
//                println("THISIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+destF.exists())

                org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
            }
            if(p0 == DELETE){
                println("sldkdobsererer")
                try{
                    var destF=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")
                    if(!destF.exists()){
                        var s=destF.mkdirs()
                        println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
                    }


//                var ff=File(destF,p1)
                    var file=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}$p1")
                    org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
//                    viewModel=ViewModelProvider(this@HomeFragment2).get(PhotoFragmentViewModel::class.java)



//                copyFil
//                temp?.copyTo(destF,false, DEFAULT_BUFFER_SIZE)
                }
                catch(e:Exception){
                    println("thdewweww  "+e)
                }
            }
        }
    }

    inner class MediaObserving(var context: Context): FileObserver(File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images").toString(),
        ALL_EVENTS
    ) {
        var List =emptyList<File>()
        override fun onEvent(p0: Int, p1: String?) {

            println("DLFJEIEOJRERPPPPPPPPPPPPPPPQQQQQQQQQQQQQQQQQQQQQQQQQFMZZZZZZZZZZZZZZZZZZZZZZZZDF")
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                if(p0== MOVED_TO){
//                    var sharedPref= context.getSharedPreferences("pref",Context.MODE_PRIVATE)
//                    var uriString=sharedPref?.getString("MEDIA_URI",null)
//
//                    var treeUri= Uri.parse(uriString)
////            requestPermission()
//                    if(treeUri!=null){
//                        var nu= Uri.parse((treeUri.toString())+"%2F.Statuses")
//                        var myUri= DocumentsContract.buildChildDocumentsUriUsingTree(
//                            treeUri,
//                            DocumentsContract.getTreeDocumentId(treeUri))
//                        val mydirNodes: MutableList<Uri> = LinkedList()
//
//                        val dirNodes: MutableList<Uri> = LinkedList()
//                        var mimeArray:MutableList<String> = mutableListOf()
//                        dirNodes.add(myUri)
//                        while (!dirNodes.isEmpty()) {
//                            myUri = dirNodes.removeAt(0) // get the item from top
//                            val c: Cursor? = requireContext().contentResolver.query(
//                                myUri,
//                                arrayOf(
//                                    DocumentsContract.Document.COLUMN_DOCUMENT_ID,
//                                    DocumentsContract.Document.COLUMN_DISPLAY_NAME,
//                                    DocumentsContract.Document.COLUMN_MIME_TYPE
//                                ),
//                                null,
//                                null,
//                                null
//                            )
//                            try {
//                                if (c != null) {
//                                    while (c.moveToNext()) {
//                                        val docId: String = c.getString(0)
//                                        val name: String = c.getString(1)
//                                        val mime: String = c.getString(2)
//
//                                        Log.d("TAG", "docId: $docId, name: $name, mime: $mime")
//                                        var newNode =
//                                            DocumentsContract.buildChildDocumentsUriUsingTree(
//                                                treeUri,
//                                                docId
//                                            )
//                                        if (docId.contains(".Statuses") && !docId.endsWith(".Statuses") && !docId.endsWith(".nomedia")) {
//                                            mydirNodes.add(newNode)
//                                            mimeArray.add(mime)
//
//                                        }
//
//                                        dirNodes.add(newNode)
//                                    }
//                                    println(" ")
//
//                                }
//
//
//                            }
//                            finally {
////                        closeQuietly(c)
//                            }
//                        }
//                        for (i in 0..mydirNodes.size-1) {
//                            var n = mydirNodes[i].pathSegments
//                            var name=n[n.size-2]
//                            var path = mydirNodes[i].path
//
//                            var f = ModelClass(path!!, name, mydirNodes[i],mimeArray[i])
//                            fileslist.add(f)
//                            println("TT    LL " + i)
//                        }
//                    }
//                }
//                if(p0 == DELETE){
//                    println("sldkdobsererer")
//                    try{
//                        var destF=File(context?.getExternalFilesDir(null)?.absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")
////                var ff=File(destF,p1)
//                        var file=File(context?.getExternalFilesDir(null)?.absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}$p1")
//                        org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
////                    viewModel=ViewModelProvider(this@HomeFragment2).get(PhotoFragmentViewModel::class.java)
//
//
//
////                copyFil
////                temp?.copyTo(destF,false, DEFAULT_BUFFER_SIZE)
//                    }
//                    catch(e:Exception){
//                        println("thdewweww  "+e)
//                    }
//                }
//
//
//            }

            if(p0== MOVED_TO){
                println("HEEELELELLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLOO1234444")
                var file:File?=null
//                if(android.os.Build.VERSION.SDK_INT>=29){
                    file=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}$p1")

//                }
//                else{
//                    file=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}$p1")
//
//                }
//
//            var srcF=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}${p1}")
                println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ")
//            println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"+srcF.absoluteFile)
//            var file=File.createTempFile("hel",".jpg")
                var destF=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}")
                println("THISIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+destF.exists())
//            temp=org.apache.commons.io.FileUtils.
                if(!destF.exists()){
                    var s=destF.mkdirs()
                    println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
                }
//                println("THISIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+destF.exists())

                org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
            }
            if(p0 == DELETE){
                println("sldkdobsererer")
                try{
                    var destF=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")
                    if(!destF.exists()){
                        var s=destF.mkdirs()
                        println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
                    }

//                var ff=File(destF,p1)
                    var file=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}$p1")
                    org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
//                    viewModel=ViewModelProvider(this@HomeFragment2).get(PhotoFragmentViewModel::class.java)



//                copyFil
//                temp?.copyTo(destF,false, DEFAULT_BUFFER_SIZE)
                }
                catch(e:Exception){
                    println("thdewweww  "+e)
                }
            }
        }
    }
    inner class MediaObserving2(var context:Context): FileObserver(File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Video").toString(),
        ALL_EVENTS
    ) {
        var List =emptyList<File>()
        override fun onEvent(p0: Int, p1: String?) {

//        println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")

//        var srcF=File(p1)
//        var temp:File?=null
            if(p0== MOVED_TO){
                var file:File?=null
//                if(android.os.Build.VERSION.SDK_INT>=29){
                file=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Video${File.separator}$p1")

//                }
//                else{
//                    file=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Video${File.separator}$p1")
//
//                }

//
//            var srcF=File(Environment.getExternalStorageDirectory(),"WhatsApp${File.separator}Media${File.separator}WhatsApp Images${File.separator}${p1}")
                println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ")
//            println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK"+srcF.absoluteFile)
//            var file=File.createTempFile("hel",".jpg")
//
//
//            temp=org.apache.commons.io.FileUtils.
                var destF=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}")
                if(!destF.exists()){
                    var s=destF.mkdirs()
                    println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
                }

                org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
            }
            if(p0 == DELETE){
                println("sldkdobsererer")
                try{
                    var destF=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}Deleted Images&Videos${File.separator}")
                    if(!destF.exists()){
                        var s=destF.mkdirs()
                        println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+s)
                    }

//                var ff=File(destF,p1)
                    var file=File(Environment.getExternalStorageDirectory().absolutePath,"WhatsApp Messiah${File.separator}All Images&Videos${File.separator}$p1")
                    org.apache.commons.io.FileUtils.copyFileToDirectory(file,destF)
//                    viewModel=ViewModelProvider(this@HomeFragment2).get(PhotoFragmentViewModel::class.java)



//                copyFil
//                temp?.copyTo(destF,false, DEFAULT_BUFFER_SIZE)


                }
                catch(e:Exception){
                    println("thdewweww  "+e)
                }
            }

        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}