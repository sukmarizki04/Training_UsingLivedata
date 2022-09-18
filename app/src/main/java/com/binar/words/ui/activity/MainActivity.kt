package com.binar.words.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.*
import com.binar.words.R
import com.binar.words.`interface`.OnDataPass
import com.binar.words.databinding.ActivityMainBinding
import com.binar.words.ui.adapter.LetterAdapter
import com.binar.words.ui.fragment.LetterFragment
import com.binar.words.ui.fragment.WordFragment

class MainActivity : AppCompatActivity(), OnDataPass{

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val letterFragment = LetterFragment()
        val fragmentManager = supportFragmentManager

        fragmentManager.commit {
            add(R.id.nav_host_fragment_container, letterFragment)
        }

    }

    fun goToNextFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.commit {
            addToBackStack(null)
            replace(R.id.nav_host_fragment_container, fragment)
        }
    }

    override fun onDataPass(letter: String) {

        val wordFragment = WordFragment()

        val bundle = Bundle()
        bundle.putString("letter", letter)

        wordFragment.arguments = bundle
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.nav_host_fragment_container, wordFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun showUpButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideUpButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun clickUpButton() {

        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                return
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    android.R.id.home -> {
                        supportFragmentManager.popBackStack()
                        true
                    }
                    else -> false
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}