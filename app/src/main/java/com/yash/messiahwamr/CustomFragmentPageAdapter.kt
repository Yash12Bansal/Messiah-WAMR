package com.yash.messiahwamr

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CustomFragmentPageAdapter(var f:FragmentManager,var b:Int): FragmentPagerAdapter(f,b) {
    var fL=ArrayList<Fragment>()

    var tL=ArrayList<String>()

    override fun getCount(): Int {
        return fL.size
    }


    fun addFragment(fragment: Fragment,title:String){
        fL.add(fragment)
        tL.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fL.get(position)
    }
}