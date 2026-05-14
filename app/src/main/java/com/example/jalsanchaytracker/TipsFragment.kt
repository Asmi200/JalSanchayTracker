package com.example.jalsanchaytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jalsanchaytracker.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WaterWealthViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.setup.observe(viewLifecycleOwner) { setup ->
            val runoff = setup?.runoffCoefficient ?: 0.80f
            val roofName = when (runoff) {
                0.75f -> "Tiled / Sloped Roof"
                0.60f -> "Rough Concrete Roof"
                0.50f -> "Gravel / Terrace Garden"
                else  -> "RCC Flat Roof"
            }

            // Update subtitle to show roof type
            binding.tvTipsSubtitle.text = "Tips for your roof: $roofName"

            val tips = getGeneralTips() + getTipsForRoof(runoff)

            val adapter = TipsAdapter(tips)
            binding.rvTips.layoutManager = LinearLayoutManager(requireContext())
            binding.rvTips.adapter = adapter
        }
    }

    private fun getGeneralTips(): List<Tip> = listOf(
        Tip("Clean your roof before monsoon",
            "Dust and debris reduce runoff quality and clog filters. Sweep the catchment area before the first rain."),
        Tip("Install a first-flush diverter",
            "The first 2mm of rainfall washes pollutants. A diverter discards this and routes clean water to your tank."),
        Tip("Check for tank leakages monthly",
            "A small crack can lose hundreds of litres per day. Inspect joints and the base every month."),
        Tip("Track rainfall patterns",
            "Log every rain event, even light ones. Monthly totals help you plan water reuse and predict lean seasons."),
        Tip("Use drip irrigation for garden",
            "Drip systems use 30-50% less water than sprinklers. Use your harvested water efficiently for plants.")
    )

    private fun getTipsForRoof(runoff: Float): List<Tip> = when (runoff) {

        0.80f -> listOf( // RCC Flat Roof
            Tip("RCC Roof — Keep it smooth",
                "Your RCC flat roof has the best runoff of 80%. Avoid placing heavy objects or planters on it as they reduce effective catchment area."),
            Tip("RCC Roof — Slope matters",
                "Ensure your roof has a slight slope of at least 1:100 towards the drain pipe. Standing water reduces quality and promotes algae growth."),
            Tip("RCC Roof — Paint with waterproof coating",
                "Applying a food-grade waterproof coating on your RCC roof improves runoff quality and can increase coefficient to 0.85."),
            Tip("RCC Roof — Clean drain pipes monthly",
                "RCC roofs collect more debris in drain pipes. Clean them before monsoon to prevent blockage and overflow loss.")
        )

        0.75f -> listOf( // Tiled Roof
            Tip("Tiled Roof — Check for broken tiles",
                "Broken or cracked tiles reduce runoff efficiency. Replace damaged tiles before monsoon season to maintain your 75% runoff rate."),
            Tip("Tiled Roof — Install gutter mesh",
                "Tiles collect moss and leaf debris easily. Install a fine mesh over gutters to filter debris before water enters the tank."),
            Tip("Tiled Roof — Clean moss regularly",
                "Moss growth on tiles absorbs water and reduces runoff. Use a mild solution to clean moss every 6 months."),
            Tip("Tiled Roof — Seal tile joints",
                "Water can seep through loose tile joints. Seal them with weatherproof mortar to improve collection efficiency.")
        )

        0.60f -> listOf( // Rough Concrete
            Tip("Rough Concrete — Surface treatment recommended",
                "Your rough concrete roof absorbs more water reducing efficiency to 60%. Consider a smooth cement plaster coat to improve runoff to 75%."),
            Tip("Rough Concrete — More frequent cleaning needed",
                "Rough surfaces trap more dirt and pollutants. Clean the roof at least twice before monsoon for better water quality."),
            Tip("Rough Concrete — Use larger tank",
                "With 60% efficiency you collect less water per rainfall. Compensate by using a larger storage tank to capture more during heavy rain."),
            Tip("Rough Concrete — Consider surface sealing",
                "Applying a concrete sealer can smooth the surface, reduce absorption and improve your runoff coefficient significantly.")
        )

        0.50f -> listOf( // Gravel / Terrace Garden
            Tip("Terrace Garden — Install sub-surface drainage",
                "Your terrace garden absorbs 50% of rainfall. Installing sub-surface drainage pipes can recover some of this water for tank storage."),
            Tip("Terrace Garden — Use separate catchment area",
                "Dedicate a portion of your terrace as a clean, non-garden catchment zone with higher runoff to improve overall collection."),
            Tip("Terrace Garden — Choose shallow-root plants",
                "Shallow-root plants absorb less water than deep-root ones. Choose herbs and grasses to reduce water loss through plant absorption."),
            Tip("Terrace Garden — Mulching reduces evaporation",
                "Using mulch in your terrace garden reduces evaporation significantly, conserving the water that does get absorbed by soil.")
        )

        else -> emptyList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}