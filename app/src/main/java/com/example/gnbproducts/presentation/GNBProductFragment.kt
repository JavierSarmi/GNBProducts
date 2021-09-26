package com.example.gnbproducts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gnbproducts.databinding.FragmentProductsBinding
import com.example.gnbproducts.presentation.adapters.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Javier Sarmiento
 */
@AndroidEntryPoint
class GNBProductFragment: Fragment(){

    private var _binding: FragmentProductsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val gnbViewModel : GNBViewModel by activityViewModels()
    private var mAdapter: ProductAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerLayout.startShimmer()
        gnbViewModel.transactionList.observe(viewLifecycleOwner, { transactionModel ->
            mAdapter = ProductAdapter(context, transactionModel.productList)
            binding.rvProducts.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
            }
            gnbViewModel.getRates()
            gnbViewModel.rateList.observe(viewLifecycleOwner, {
                (binding.rvProducts.adapter as ProductAdapter).initRateList(it)
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = GONE
            })

        })

        gnbViewModel.getTransactions()
    }
}