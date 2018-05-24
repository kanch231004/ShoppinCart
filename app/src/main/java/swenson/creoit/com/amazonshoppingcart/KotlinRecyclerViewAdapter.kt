package swenson.creoit.com.amazonshoppingcart

import android.content.ContentValues.TAG
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.system.Os.bind
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.text.Typography.times

class KotlinRecyclerViewAdapter<M,H : RecyclerView.ViewHolder>(val context : Context,
                                                               val layoutID : Int,
                                                               val items : ArrayList<M>,
                                                               val count : Int,
                                                               val holderMaker :(view : View) -> H,
                                                               val bind : (holder : H ,position : Int, items : ArrayList<M>) -> Unit) : RecyclerView.Adapter<H>() {

    private val TAG: String = "KotlinRecycler"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {

        d(TAG, ": called so many times")

        val view = LayoutInflater.from(context).inflate(layoutID,parent,false)
        return holderMaker(view)
    }

    override fun getItemCount(): Int {

        return if (count == 0) items.size else count
    }

    override fun onBindViewHolder(holder: H, position: Int) {

     bind(holder,position,items)

    }
}