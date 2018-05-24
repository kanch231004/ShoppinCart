package swenson.creoit.com.amazonshoppingcart

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_discover.*
import android.view.View
import kotlinx.android.synthetic.main.activity_category_items.*


class CategoryFullListActivity : AppCompatActivity() {

    private lateinit var movieAdapter: KotlinRecyclerViewAdapter<MyMovie,CategoryVH>
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvDiscover.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvDiscover.layoutManager = GridLayoutManager(this, 4)
        }

        rlHeader.visibility = View.GONE



        var movieList = intent.getSerializableExtra("list") as ArrayList<MyMovie>
        displayFullList(movieList)



    }

    private fun displayFullList(movieList : ArrayList<MyMovie>) {

        movieAdapter = KotlinRecyclerViewAdapter(this, R.layout.activity_category_items, movieList, count,{

            CategoryVH(it)

        }, {
            holder, position, items -> holder.bindCategoryView(items[position],true)
        })

        rvDiscover.adapter = movieAdapter
        movieAdapter.notifyDataSetChanged()

    }
}