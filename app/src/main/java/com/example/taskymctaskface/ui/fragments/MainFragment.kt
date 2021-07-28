package com.example.taskymctaskface.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskymctaskface.data.local.model.Counter
import com.example.taskymctaskface.databinding.FragmentMainBinding
import com.example.taskymctaskface.ui.adapter.MainActivityAdapter
import com.example.taskymctaskface.ui.viewmodels.MainActivityViewModel


class MainFragment : Fragment() {
    private val message = "Welcome"
    private val toastLength = Toast.LENGTH_LONG

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainActivityViewModel
    private val mainActivityAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter(this::onItemClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toast = Toast.makeText(activity, message, toastLength)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        toast.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.counterList.observe(viewLifecycleOwner) {
            mainActivityAdapter.submitList(it)
            mainActivityAdapter.loadCounters(it)
        }
        viewModel.toastMessage.observe(viewLifecycleOwner){
            Toast.makeText(activity, "You clicked $it", Toast.LENGTH_LONG).show()
        }
        with(binding){
            mainRv.apply {
                adapter = mainActivityAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                addNewNumberBtn.setOnClickListener {
                    viewModel.addNewCounter()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(counter: Counter) {
        viewModel.toastCounter(counter)
    }

}

