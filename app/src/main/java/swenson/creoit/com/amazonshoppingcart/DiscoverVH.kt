package swenson.creoit.com.amazonshoppingcart

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_discover_items.view.*

class DiscoverVH(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var itemAdapter : KotlinRecyclerViewAdapter<MyMovie,CategoryVH>



    fun bindView(items :MyMovie, list : ArrayList<MyMovie>, count : Int) {

        with(itemView) {

            val intent = Intent(context,CategoryFullListActivity::class.java)
            tvCategory1.text = items.title
            tvViewAll.text = "View All"
            rvCategoryItems.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

            itemAdapter = KotlinRecyclerViewAdapter(context,R.layout.activity_category_items,list,count, {CategoryVH(it)},
                    { holder, position, items->  holder.bindCategoryView(items[position],false)})
            rvCategoryItems.adapter = itemAdapter

            tvViewAll.setOnClickListener {

                intent.putExtra("list",list)
                context?.startActivity(intent)
            }

        }
    }




}

