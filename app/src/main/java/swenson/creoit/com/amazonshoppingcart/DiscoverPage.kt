package swenson.creoit.com.amazonshoppingcart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.system.Os.bind
import android.util.Log.d
import android.widget.Toast

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_discover.*


class DiscoverPage : AppCompatActivity() {

    private val TAG: String = "DiscoverPage"
    private  var myMovie : ArrayList<MyMovie>? = null
    private lateinit var movieAdapter: KotlinRecyclerViewAdapter<MyMovie,DiscoverVH>
    private var count = 0


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("count",count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState?.getInt("count", -1) ?: 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        val llManager = LinearLayoutManager(this)
        rvDiscover.layoutManager = llManager


        getMoviesList()

        btnNo.setOnClickListener{

           count =  Integer.parseInt(etNo.text.toString())

            if (count > 10) {
                Toast.makeText(this, "Maximum can be 10", Toast.LENGTH_SHORT).show()

            }

            else {
                getMoviesList()
            }

        }

    }

    private fun getMoviesList() {

        val observable = ApiInterface.create().getMoviesName().observeOn(
                AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())

        observable.subscribe( {

            d(TAG, "response code : ${it.code()}")
            if (it.code() == 200) {

                myMovie = it.body()
                displayContent()

                d(TAG, "my movie: ${it.body()}")
            }

        }, {

           d(TAG, "error  ${it.localizedMessage}: ")

        })
    }

   private fun displayContent() {

       myMovie?.let {

           movieAdapter = KotlinRecyclerViewAdapter(this, R.layout.activity_discover_items, it, count,{

               DiscoverVH(it)

           }, {

              holder, position, items -> holder.bindView(items[position],items,count)
           })

           rvDiscover.adapter = movieAdapter
           movieAdapter.notifyDataSetChanged()
       }
   }
}