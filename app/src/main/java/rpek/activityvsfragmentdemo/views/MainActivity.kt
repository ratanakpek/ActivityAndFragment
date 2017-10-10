package rpek.activityvsfragmentdemo.views


import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import rpek.activityvsfragmentdemo.R
import rpek.activityvsfragmentdemo.fragments.CartFragment
import rpek.activityvsfragmentdemo.fragments.HomeFragment
import rpek.activityvsfragmentdemo.fragments.SignInFragment

class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener, CartFragment.OnFragmentInteractionListener, SignInFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.inTransaction {
            add(R.id.fragment_container, HomeFragment())
        }

        bottomNavigationView.setOnNavigationItemSelectedListener{
            item->
            when(item.itemId){
                R.id.action_home->{
                    supportFragmentManager.inTransaction {
                        replace(R.id.fragment_container, HomeFragment())
                    }
                }
                R.id.action_offline->{
                    supportFragmentManager.inTransaction {
                        replace(R.id.fragment_container, CartFragment())
                    }
                }
                R.id.action_user->{
                    supportFragmentManager.inTransaction {
                        replace(R.id.fragment_container, SignInFragment())
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
