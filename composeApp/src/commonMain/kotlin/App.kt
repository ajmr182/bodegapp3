import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import ui.features.home.HomeScreen

@Composable
fun App() {
    MaterialTheme {
      Navigator(HomeScreen()){
          SlideTransition(it)
      }
    }
}
