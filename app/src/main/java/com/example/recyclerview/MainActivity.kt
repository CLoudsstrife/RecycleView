package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val _arWayang = ArrayList<DcWayang>()

    private var _nama: Array<String> = arrayOf()
    private var _karakter: Array<String> = arrayOf()
    private var _deskripsi: Array<String> = arrayOf()
    private var _gambar: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        siapkanData()
        tambahData()
        tampilkanData()

        // Langkah 38: Listener untuk mengganti tampilan saat tombol ditekan
        binding.btnToggleLayout.setOnCheckedChangeListener { _, isChecked ->
            tampilkanData()
        }
    }

    private fun siapkanData() {
        _nama = resources.getStringArray(R.array.namaWayang)
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang)
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang)
        _gambar = resources.getStringArray(R.array.gambarWayang)
    }

    private fun tambahData() {
        _arWayang.clear()
        for (i in _nama.indices) {
            val wayang = DcWayang(
                foto = _gambar[i],
                nama = _nama[i],
                karakter = _karakter[i],
                deskripsi = _deskripsi[i]
            )
            _arWayang.add(wayang)
        }
    }

    private fun tampilkanData() {
        if (binding.btnToggleLayout.isChecked) {
            binding.rvWayang.layoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        } else {
            binding.rvWayang.layoutManager = LinearLayoutManager(this)
        }

        binding.rvWayang.adapter = AdapterRecView(_arWayang)
    }
}