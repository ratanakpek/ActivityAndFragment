package rpek.activityvsfragmentdemo.views


import android.net.Uri
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.muddzdev.styleabletoastlibrary.StyleableToast
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import rpek.activityvsfragmentdemo.R
import rpek.activityvsfragmentdemo.fragments.CartFragment
import rpek.activityvsfragmentdemo.fragments.HomeFragment
import rpek.activityvsfragmentdemo.fragments.SignInFragment
import android.view.Gravity
import android.view.View
import com.github.florent37.awesomebar.ActionItem
import com.github.florent37.awesomebar.AwesomeBar



class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener, CartFragment.OnFragmentInteractionListener, SignInFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // var drawerLayout = drawerLayout

        bar.addAction(R.drawable.awsb_ic_edit_animated, "Compose");
        bar.setActionItemClickListener { position, actionItem -> Toast.makeText(baseContext, actionItem.text + " clicked", Toast.LENGTH_LONG).show() }

//        bar.setOnMenuClickedListener(object : View.OnClickListener() {
//            fun onClick(v: View) {
//                 drawerLayout.openDrawer(Gravity.START)
//            }
//        })

        //bar.displayHomeAsUpEnabled(true / false)


        supportFragmentManager.inTransaction {
            add(R.id.fragment_container, HomeFragment())
        }

        bottomNavigationView.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.fragment_container, HomeFragment())
                        //Toasty.success(this@MainActivity, "HomeScreen", Toast.LENGTH_SHORT).show()
                        StyleableToast.makeText(this@MainActivity, "Hello World!", Toast.LENGTH_LONG, R.style.MyToast).show();
                    }
                }
                R.id.action_offline -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.fragment_container, CartFragment())
                        //Toasty.error(this@MainActivity, "Offline", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.action_user -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.fragment_container, SignInFragment())
                       // Toasty.normal(this@MainActivity, "SignIn", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            true
        }

    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

}
