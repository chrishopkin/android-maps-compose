package com.google.maps.android.compose

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.platform.ComposeView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.Marker

public class ComposeInfoWindowContentAdapter(
    private val mapView: MapView,
    private val markerInfoContent: @Composable (Marker) -> Unit,
    private val hasMarkerInfoContent: (Marker) -> Boolean = { true },
    private val compositionContext: CompositionContext,
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker): View? {
        if (!hasMarkerInfoContent(marker)) return null

        val view = ComposeView(mapView.context).apply {
            setContent { markerInfoContent(marker) }
        }
        mapView.renderComposeInfoWindowView(view, parentContext = compositionContext)
        return view
    }

    override fun getInfoWindow(marker: Marker): View? = null
}
