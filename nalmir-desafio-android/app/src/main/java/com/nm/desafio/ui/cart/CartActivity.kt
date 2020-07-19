package com.nm.desafio.ui.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.nm.desafio.R
import com.nm.domain.entity.Cart
import com.nm.infra.base.BaseActivity
import com.nm.infra.base.BaseViewModel
import com.nm.infra.extensions.bindVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: CartViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        bindVM(viewModel.cart, ::processCart)

        viewModel.getCart()
    }

    private fun iniActions() {

    }

    private fun processCart(cart: Cart) {
        var i = 10
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}