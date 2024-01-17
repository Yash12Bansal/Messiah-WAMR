package com.yash.messiahwamr

//import com.yash.messiah.R

import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.storage.StorageManager
import android.provider.DocumentsContract
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.util.FileUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.core.app.ApplicationProvider
import com.yash.messiahwamr.adapters.StatusAdapter
import com.yash.messiahwamr.models.ModelClass
import com.yash.messiahwamr.models.ModelClass2
import kotlinx.android.synthetic.main.fragment_status_home.*
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import java.io.Closeable
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [statusHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class statusHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter: StatusAdapter
    lateinit var files:Array<File>
    var fileslist:ArrayList<ModelClass> =arrayListOf()
//    var fileslist:ArrayList<ModelClass2> =arrayListOf()

    lateinit var recyclerview: RecyclerView
    lateinit var refreshLayout: SwipeRefreshLayout


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
        return inflater.inflate(R.layout.fragment_status_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview=view.findViewById(R.id.recyclerview)
        refreshLayout=view.findViewById(R.id.swipe)
//        supportActionBar?.setTitle("WhatsApp Status")
//        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.actionbar)))

//        val builder1: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
//        builder1.setMessage("Open your Whatsapp -> Watch FULL status you want to save -> It will be visible here to download")
//        builder1.setCancelable(true)
//
//        builder1.setPositiveButton(
//            "OK",
//            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
//
////        builder1.setNegativeButton(
////            "No",
////            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
//
//        val alert11: AlertDialog = builder1.create()
//        alert11.show()


        setuplayout()
        downloaded.setOnClickListener {
            startActivity(Intent(requireActivity(), DownloadedStatus::class.java))
        }

        refreshLayout.setOnRefreshListener {
            refreshLayout.setRefreshing(true)
            setuplayout()

            Handler().postDelayed(object: Runnable {
                override fun run() {
                    refreshLayout.setRefreshing(false)

                }
            },500)
        }
    }

    private fun setuplayout() {

        fileslist?.clear()
        recyclerview.setHasFixedSize(true)
        var staggeredGridLayoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recyclerview.layoutManager=staggeredGridLayoutManager
        println("helfldfdlkfjdlkfjdklfjdkfjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj")

        adapter= StatusAdapter(requireActivity(),getData())
        println("helfldfdlkfjdlkfjdklfjdkfjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj")
//        println(?.filename)
        println("dfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff       "+fileslist)
        println("dfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff       "+fileslist.size)

        recyclerview.adapter=adapter
        adapter.notifyDataSetChanged()
    }
    private fun requestPermission() {
//        if (SDK_INT >= Build.VERSION_CODES.R) {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.addCategory("android.intent.category.DEFAULT")
            intent.data =
                Uri.parse(
                    String.format(
                        "com.whatsapp",
//                        "package:%s",
                        ApplicationProvider.getApplicationContext<Context>().getPackageName()
                    )
                )
            startActivityForResult(intent, 2296)
        } catch (e: Exception) {
            val intent = Intent()
            intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
            startActivityForResult(intent, 2296)
        }
//    }
        //        else {
//            //below android 11
//            ActivityCompat.requestPermissions(
//                this@PermissionActivity,
//                arrayOf(WRITE_EXTERNAL_STORAGE),
//                1234
//            )
//        }
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun getFolderPermission(application: Application? =null){
        var storageManager=requireActivity().getSystemService(Context.STORAGE_SERVICE) as StorageManager
        var intent=Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply{
            putExtra(DocumentsContract.EXTRA_INITIAL_URI,Uri.parse(Environment.getExternalStorageDirectory().absolutePath+"/Android/media/com.whatsapp/WhatsApp/Media"))
        }
//        var intent=storageManager.primaryStorageVolume.createOpenDocumentTreeIntent()
//        var targetDirectory="Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses"
////        var targetDirectory2="Android/media/com.whatsapp/WhatsApp/Media"
//        var uri=intent.getParcelableExtra<Uri>("android.provider.extra.INITIAL_URI")as Uri
//
//        var scheme=uri.toString()
//        scheme=scheme.replace("/root/","/tree/")
//        scheme+="%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp"
//        uri=Uri.parse(scheme)
//
//        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI,Uri.parse(Environment.getExternalStorageDirectory().absolutePath+"/Android/media/com.whatsapp/WhatsApp/Media"))
//        intent.putExtra("android.content.extra.SHOW_ADVANCED",true)
        startActivityForResult(intent,1234)
    }
//    fun traverseDirectoryEntries(rootUri: Uri?) {
//        val contentResolver = requireActivity().contentResolver
//        var childrenUri = DocumentsContract.buildChildDocumentsUriUsingTree(
//            rootUri,
//            DocumentsContract.getTreeDocumentId(rootUri)
//        )
//
//        // Keep track of our directory hierarchy
//        val dirNodes: MutableList<Uri> = LinkedList()
//        dirNodes.add(childrenUri)
//        while (!dirNodes.isEmpty()) {
//            childrenUri = dirNodes.removeAt(0) // get the item from top
//
//            Log.d("GGGGGGGGGGG",  childrenUri.toString())
//            val c: Cursor? = contentResolver.query(
//                childrenUri,
//                arrayOf(
//                    DocumentsContract.Document.COLUMN_DOCUMENT_ID,
//                    DocumentsContract.Document.COLUMN_DISPLAY_NAME,
//                    DocumentsContract.Document.COLUMN_MIME_TYPE
//                ),
//                null,
//                null,
//                null
//            )
//            try {
//                if (c != null) {
//                    while (c.moveToNext()) {
//                        val docId: String = c.getString(0)
//                        val name: String = c.getString(1)
//                        val mime: String = c.getString(2)
//                        Log.d("TAG", "docId: $id, name: $name, mime: $mime")
//                        if (isDirectory(mime)) {
//                            val newNode =
//                                DocumentsContract.buildChildDocumentsUriUsingTree(rootUri, docId)
//                            dirNodes.add(newNode)
//                        }
//                    }
//                }
//            } finally {
//                closeQuietly(c)
//            }
//        }
//    }
//
//    // Util method to check if the mime type is a directory
//    private fun isDirectory(mimeType: String): Boolean {
//        return DocumentsContract.Document.MIME_TYPE_DIR == mimeType
//    }
//
//    // Util method to close a closeable
//    private fun closeQuietly(closeable: Closeable?) {
//        if (closeable != null) {
//            try {
//                closeable.close()
//            } catch (re: RuntimeException) {
//                throw re
//            } catch (ignore: java.lang.Exception) {
//                // ignore exception
//            }
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==RESULT_OK){
            var uuri:Uri?=null
            var treeUri=data?.data
            var id=data?.identifier
            if(treeUri!=null){
                var nu=Uri.parse((treeUri.toString())+"%2F.Statuses")
//                var uu=DocumentsContract.buildDocumentUriUsingTree(treeUri,DocumentsContract.getDocumentId(treeUri))
//                println(uu)

                var myUri=DocumentsContract.buildChildDocumentsUriUsingTree(
                    treeUri,
                    DocumentsContract.getTreeDocumentId(treeUri))
                val mydirNodes: MutableList<Uri> = LinkedList()

                val dirNodes: MutableList<Uri> = LinkedList()
                var mimeArray:MutableList<String> = mutableListOf()
                dirNodes.add(myUri)
//                println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii")
//                for(i in dirNodes){
//                    println(i)
//                }

                while (!dirNodes.isEmpty()) {
                    myUri = dirNodes.removeAt(0) // get the item from top
//                    println("GGGGGGGGGGG"+myUri)
//                    println("GGGGGGGGGGG"+myUri.lastPathSegment)

//                    if(myUri.toString().contains(".Statuses")){
                        val c: Cursor? = requireContext().contentResolver.query(
                            myUri,
                            arrayOf(
                                DocumentsContract.Document.COLUMN_DOCUMENT_ID,
                                DocumentsContract.Document.COLUMN_DISPLAY_NAME,
                                DocumentsContract.Document.COLUMN_MIME_TYPE
                            ),
                            null,
                            null,
                            null
                        )
                        try {
                            if (c != null) {
                                while (c.moveToNext()) {
                                    val docId: String = c.getString(0)
                                    val name: String = c.getString(1)
                                    val mime: String = c.getString(2)

                                    Log.d("TAG", "docId: $docId, name: $name, mime: $mime")
                                        var newNode =
                                            DocumentsContract.buildChildDocumentsUriUsingTree(
                                                treeUri,
                                                docId
                                            )
                                    if (docId.contains(".Statuses") && !docId.endsWith(".Statuses") && !docId.endsWith(".nomedia")) {
                                        mydirNodes.add(newNode)
                                        mimeArray.add(mime)

                                    }

                                        dirNodes.add(newNode)

//                                        println("NEWNODE" + newNode)

//                                        while(!dirNodes.isEmpty()){
//                                            newNode=dirNodes.removeAt(0)
//                                            val c2: Cursor? = requireContext().contentResolver.query(
//                                                newNode,
//                                                arrayOf(
//                                                    DocumentsContract.Document.COLUMN_DOCUMENT_ID,
//                                                    DocumentsContract.Document.COLUMN_DISPLAY_NAME,
//                                                    DocumentsContract.Document.COLUMN_MIME_TYPE
//                                                ),
//                                                null,
//                                                null,
//                                                null
//                                            )
//                                            if(c2!=null){
//                                                while(c2.moveToNext()){
//
//                                                    val docId2: String = c2.getString(0)
//                                                    val name2: String = c2.getString(1)
//                                                    val mime2: String = c2.getString(2)
//                                                    Log.d("TAG", "docId2: $docId2, name2: $name2, mime2: $mime2")
//
//
//
//                                                }
//                                                println("OOOQOQQQQQQQQPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP")
//                                                for(i in dirNodes){
//                                                    println(i)
//                                                }
//                                            }
//
//
//                                        }


//                                        for (i in dirNodes) {
//                                            var n = name
//                                            var path = i.path
//                                            var f = ModelClass(path!!, n, i)
//                                            println("TT    LL "+i)
////                    var f= ModelClass(uri.toString(),name,uri)
//                                        if(!f.uri.toString().endsWith(".nomedia")){
//                                            println("KLLLZZZZZZZZZZ"+f.filename)
//                                            println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
//                                            println(i.javaClass.name)
//                                            println(i)
//                                            println(f.uri)
//                                            println(docId)
//                                            println("HHHHHHHHHHHHHHHHHH"+ !docId.endsWith(".Statuses"))
//?****************************
//                                            if (!docId.endsWith(".Statuses")) {
//                                                println("TTHTTTHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")
//                                                println(f.uri)
//                                                fileslist.add(f)
//                                                val inputStream: InputStream? =
//                                                    requireContext().contentResolver.openInputStream(
//                                                        i
//                                                    )
//                                                var uu =
//                                                    Uri.parse(requireContext().getFilesDir()?.absolutePath + "/WhatsApp Messiah/Downloaded Status")
//                                                var ff = File(uu.toString())
//                                                *************************************
//                                                var nf=
//                                                var nf=ff.createNewFile().

//                                                var ee= com.statussaverwhatsapp.FileUtil.getFullPathFromTreeUri(i,requireContext())

//                                                var fff= kotlin.io.path.createTempFile(uu.toString())
//                                                var fileee=File(fff.toUri())
//                                                var myFileUri=FileProvider.getUriForFile(requireContext(),
//                                                    "com.statussaverwhatsapp.whatsappstatus",ff)

//                                                var k=Uri.fromFile(File(fff.toString()))
//
//                                                val outputStream: OutputStream? =
//                                                    requireContext().contentResolver.openOutputStream(k)
//
//
//                                                IOUtils.copy(inputStream, outputStream)
//                                                var mf=DocumentFile.fromTreeUri(requireContext(),i)
//                                                println("ee  "+ee)
//                                                println(i)
//                                                org.apache.commons.io.FileUtils.copyFileToDirectory(File(ee),File(requireContext().applicationContext.getFilesDir()?.absolutePath+"/WhatsApp Messiah/Downloaded Status/"))
//**************************************************

//                                            }

//                                        }
//                                    }
//                                       *************************
                                    //                                        println("MMMMMMMMMMMMMMMMMMMMMMM"+fileslist.size)

                                }
                                println(" ")

                            }


                        }
                        finally {
//                        closeQuietly(c)
                        }
                }
                for (i in 0..mydirNodes.size-1) {
                    var n = mydirNodes[i].pathSegments
                    var name=n[n.size-2]
                    var path = mydirNodes[i].path

                    var f = ModelClass(path!!, name, mydirNodes[i],mimeArray[i])
                    fileslist.add(f)
                    println("TT    LL " + i)
                }
            }
        }
    }
//
//    fun readDataFromPrefs():Boolean{
//        var sh=
//    }
    fun getData(): ArrayList<ModelClass>?{

        lateinit var f:ModelClass
        lateinit var targetpath:String

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
//            var sharedPref= this.activity?.getSharedPreferences("pref",Context.MODE_PRIVATE)
//            var uriString=sharedPref?.getString("MEDIA_URI",null)
//
//            var treeUri=Uri.parse(uriString)
////            requestPermission()
//            if(treeUri!=null){
//                var nu=Uri.parse((treeUri.toString())+"%2F.Statuses")
////                var uu=DocumentsContract.buildDocumentUriUsingTree(treeUri,DocumentsContract.getDocumentId(treeUri))
////                println(uu)
//
//                var myUri=DocumentsContract.buildChildDocumentsUriUsingTree(
//                    treeUri,
//                    DocumentsContract.getTreeDocumentId(treeUri))
//                val mydirNodes: MutableList<Uri> = LinkedList()
//
//                val dirNodes: MutableList<Uri> = LinkedList()
//                var mimeArray:MutableList<String> = mutableListOf()
//                var nameArray:MutableList<String> = mutableListOf()
//
//                dirNodes.add(myUri)
////                println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii")
////                for(i in dirNodes){
////                    println(i)
////                }
//
//                while (!dirNodes.isEmpty()) {
//                    myUri = dirNodes.removeAt(0) // get the item from top
////                    println("GGGGGGGGGGG"+myUri)
////                    println("GGGGGGGGGGG"+myUri.lastPathSegment)
//
////                    if(myUri.toString().contains(".Statuses")){
//                    val c: Cursor? = requireContext().contentResolver.query(
//                        myUri,
//                        arrayOf(
//                            DocumentsContract.Document.COLUMN_DOCUMENT_ID,
//                            DocumentsContract.Document.COLUMN_DISPLAY_NAME,
//                            DocumentsContract.Document.COLUMN_MIME_TYPE
//                        ),
//                        null,
//                        null,
//                        null
//                    )
//                    try {
//                        if (c != null) {
//                            while (c.moveToNext()) {
//                                val docId: String = c.getString(0)
//                                val name: String = c.getString(1)
//                                val mime: String = c.getString(2)
//
//                                Log.d("TAG", "docId: $docId, name: $name, mime: $mime")
//                                var newNode =
//                                    DocumentsContract.buildChildDocumentsUriUsingTree(
//                                        treeUri,
//                                        docId
//                                    )
//                                if (docId.contains(".Statuses") && !docId.endsWith(".Statuses") && !docId.endsWith(".nomedia")) {
//                                    mydirNodes.add(newNode)
//                                    mimeArray.add(mime)
//                                    nameArray.add(name)
//
//                                }
//
//                                dirNodes.add(newNode)
//
////                                        println("NEWNODE" + newNode)
//
////                                        while(!dirNodes.isEmpty()){
////                                            newNode=dirNodes.removeAt(0)
////                                            val c2: Cursor? = requireContext().contentResolver.query(
////                                                newNode,
////                                                arrayOf(
////                                                    DocumentsContract.Document.COLUMN_DOCUMENT_ID,
////                                                    DocumentsContract.Document.COLUMN_DISPLAY_NAME,
////                                                    DocumentsContract.Document.COLUMN_MIME_TYPE
////                                                ),
////                                                null,
////                                                null,
////                                                null
////                                            )
////                                            if(c2!=null){
////                                                while(c2.moveToNext()){
////
////                                                    val docId2: String = c2.getString(0)
////                                                    val name2: String = c2.getString(1)
////                                                    val mime2: String = c2.getString(2)
////                                                    Log.d("TAG", "docId2: $docId2, name2: $name2, mime2: $mime2")
////
////
////
////                                                }
////                                                println("OOOQOQQQQQQQQPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP")
////                                                for(i in dirNodes){
////                                                    println(i)
////                                                }
////                                            }
////                                        }
////                                        for (i in dirNodes) {
////                                            var n = name
////                                            var path = i.path
////                                            var f = ModelClass(path!!, n, i)
////                                            println("TT    LL "+i)
//////                    var f= ModelClass(uri.toString(),name,uri)
////                                        if(!f.uri.toString().endsWith(".nomedia")){
////                                            println("KLLLZZZZZZZZZZ"+f.filename)
////                                            println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
////                                            println(i.javaClass.name)
////                                            println(i)
////                                            println(f.uri)
////                                            println(docId)
////                                            println("HHHHHHHHHHHHHHHHHH"+ !docId.endsWith(".Statuses"))
////?****************************
////                                            if (!docId.endsWith(".Statuses")) {
////                                                println("TTHTTTHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")
////                                                println(f.uri)
////                                                fileslist.add(f)
////                                                val inputStream: InputStream? =
////                                                    requireContext().contentResolver.openInputStream(
////                                                        i
////                                                    )
////                                                var uu =
////                                                    Uri.parse(requireContext().getFilesDir()?.absolutePath + "/WhatsApp Messiah/Downloaded Status")
////                                                var ff = File(uu.toString())
////                                                *************************************
////                                                var nf=
////                                                var nf=ff.createNewFile().
//
////                                                var ee= com.statussaverwhatsapp.FileUtil.getFullPathFromTreeUri(i,requireContext())
//
////                                                var fff= kotlin.io.path.createTempFile(uu.toString())
////                                                var fileee=File(fff.toUri())
////                                                var myFileUri=FileProvider.getUriForFile(requireContext(),
////                                                    "com.statussaverwhatsapp.whatsappstatus",ff)
//
////                                                var k=Uri.fromFile(File(fff.toString()))
////
////                                                val outputStream: OutputStream? =
////                                                    requireContext().contentResolver.openOutputStream(k)
////
////
////                                                IOUtils.copy(inputStream, outputStream)
////                                                var mf=DocumentFile.fromTreeUri(requireContext(),i)
////                                                println("ee  "+ee)
////                                                println(i)
////                                                org.apache.commons.io.FileUtils.copyFileToDirectory(File(ee),File(requireContext().applicationContext.getFilesDir()?.absolutePath+"/WhatsApp Messiah/Downloaded Status/"))
////**************************************************
//
////                                            }
//
////                                        }
////                                    }
////                                       *************************
//                                //                                        println("MMMMMMMMMMMMMMMMMMMMMMM"+fileslist.size)
//
//                            }
//                            println(" ")
//
//                        }
//
//
//                    }
//                    finally {
////                        closeQuietly(c)
//                    }
//                }
//                for (i in 0..mydirNodes.size-1) {
//                    var n = mydirNodes[i].pathSegments
//                    var name=nameArray[i]
//                    var path = mydirNodes[i].path
//
//                    var f = ModelClass(path!!, name, mydirNodes[i],mimeArray[i])
//                    fileslist.add(f)
//                    println("TT    LL " + i)
//                }
//            }

            targetpath= Environment.getExternalStorageDirectory().absolutePath+"/Android/media/com.whatsapp/WhatsApp/Media/.Statuses"
            var targetdir=File(targetpath)


            if(targetdir.listFiles()!=null){
                files=targetdir.listFiles()
                println(targetdir)

                println("EEEEERRRTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+files.size)
                for(i in 0..files.size-1){
                    var file=files[i]
                    var path=file.absolutePath
                    var name=file.name

                    println(file)
                    println(path)
                    println(name)

                    var uri= Uri.fromFile(file)

                    println(uri)

                    f= ModelClass(path,name,uri)
                    if(!f.uri.toString().endsWith(".nomedia")){
                        fileslist.add(f)

                    }
                    println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+fileslist.size)

                }


                println("fddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")


            }

        }
        else{
            targetpath= Environment.getExternalStorageDirectory().absolutePath+"/WhatsApp/Media/.Statuses"
            var targetdir=File(targetpath)


            if(targetdir.listFiles()!=null){
                files=targetdir.listFiles()
                println(targetdir)

                println("EEEEERRRTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+files.size)
                for(i in 0..files.size-1){
                    var file=files[i]
                    var path=file.absolutePath
                    var name=file.name

                    println(file)
                    println(path)
                    println(name)

                    var uri= Uri.fromFile(file)

                    println(uri)

                    f= ModelClass(path,name,uri)
                    if(!f.uri.toString().endsWith(".nomedia")){
                        fileslist.add(f)

                    }
                    println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+fileslist.size)

                }


                println("fddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")


            }

        }
        return fileslist

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment statusHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            statusHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}