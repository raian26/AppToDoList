package com.generation.todo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.generation.todo.databinding.FragmentFormBinding
import com.generation.todo.databinding.FragmentListBinding
import com.generation.todo.fragment.DatePickerFragment
import com.generation.todo.fragment.TimerPickerListener
import com.generation.todo.model.Categoria
import com.generation.todo.model.Tarefa
import java.time.LocalDate


class FormFragment : Fragment(), TimerPickerListener {
    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var categoriaSelecioanda = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        mainViewModel.listCategoria()

        mainViewModel.dataSelecionada.value = LocalDate.now()

        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner) { response ->
            Log.d("Requisição", response.body().toString())
            spinnerCategoria(response.body())
        }

        mainViewModel.dataSelecionada.observe(viewLifecycleOwner) { selectDate ->
            binding.editData.setText(selectDate.toString())
        }

        // Inflate the layout for this fragment
        binding.buttonSalvar.setOnClickListener {
            inserirBanco()

        }

        binding.editData.setOnClickListener {
            DatePickerFragment(this).show(parentFragmentManager, "DatePicker")
        }

        return binding.root
    }

    private fun spinnerCategoria(listCategoria: List<Categoria>?) {
        if (listCategoria != null) {
            binding.spinnerCategoria.adapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listCategoria
            )

            binding.spinnerCategoria.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selected = binding.spinnerCategoria.selectedItem as Categoria

                        categoriaSelecioanda = selected.id

                    }



                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

            }

        }

    private fun validarCampos(
        nome: String, descricao: String, responsavel: String
    ): Boolean {
        return !(
                (nome == "" || nome.length <3 || nome.length>20) ||
                        (descricao == "" || descricao.length<5 || descricao.length>200) ||
                        (responsavel == "" || responsavel.length <3 || responsavel.length>20)
                )}

    private fun inserirBanco(){
        var nome = binding.editNome.text.toString()
        var desc = binding.editDescricao.text.toString()
        var resp = binding.editResponsavel.text.toString()
        var data = binding.editData.text.toString()
        var status = binding.switchAtivoCard.isChecked
        var categoria = Categoria(categoriaSelecioanda, null, null)

        if(validarCampos(nome, desc, resp)){
            var tarefa = Tarefa(0,nome,desc,resp,data,status,categoria)
            mainViewModel.addTarefa(tarefa)
            Toast.makeText(context, "Tarefa criada com sucesso!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_formFragment2_to_listFragment)
        }
        else{
            Toast.makeText(context,"Verifique se todos os campos foram preenchidos corretamente!",
          Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDateSelected(date: LocalDate) {
        mainViewModel.dataSelecionada.value = date
    }

}
