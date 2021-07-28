package com.example.taskymctaskface.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskymctaskface.R
import com.example.taskymctaskface.data.local.model.Counter
import com.example.taskymctaskface.databinding.FragmentMainBinding
import com.example.taskymctaskface.ui.adapter.MainActivityAdapter
import com.example.taskymctaskface.ui.viewmodels.MainActivityViewModel
import kotlin.random.Random


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
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val toast = Toast.makeText(activity, message, toastLength)
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
                setHasFixedSize(true)
                addNewNumberBtn.setOnClickListener {
                    viewModel.addNewCounter()
                }
            }
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.linear_btn -> {
                        it.isVisible = false
                        val gb = toolbar.menu.findItem(R.id.grid_btn)
                        gb.isVisible = true
                        mainRv.layoutManager = LinearLayoutManager(requireContext())
                        mainRv.post {
                            for(i in 0 until mainRv.childCount){
                                val newColor = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                                val holder = mainRv.findViewHolderForAdapterPosition(i)
                                holder?.itemView?.setBackgroundColor(newColor)
                            }
                        }

                        true
                    }
                    R.id.grid_btn -> {
                        it.isVisible = false
                        val lb = toolbar.menu.findItem(R.id.linear_btn)
                        lb.isVisible = true
                        mainRv.layoutManager = GridLayoutManager(requireContext(), 4)
                        mainRv.post {
                            for(i in 0 until mainRv.childCount){
                                val newColor = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                                val holder = mainRv.findViewHolderForAdapterPosition(i)
                                holder?.itemView?.setBackgroundColor(newColor)
                            }
                        }
                        true
                    }
                    else -> false
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

