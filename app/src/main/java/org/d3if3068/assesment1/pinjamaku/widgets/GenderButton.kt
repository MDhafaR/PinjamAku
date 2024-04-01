package org.d3if3068.assesment1.pinjamaku.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3068.assesment1.pinjamaku.ui.theme.Utama
import org.d3if3068.assesment1.pinjamaku.ui.theme.UtamaBerat

@Composable
fun GenderButton(
    jenisKelamin: String,
    isSelected: Boolean,
    onJenisKelaminSelected: (String) -> Unit,
    error: Boolean
) {
    val warnaError = if (error) Color.Red else Utama

    Row(
        modifier = Modifier.clickable { onJenisKelaminSelected(jenisKelamin) }.padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onJenisKelaminSelected(jenisKelamin) },
            colors = RadioButtonDefaults.colors(
                unselectedColor = warnaError,
                selectedColor = UtamaBerat
            )
        )
        Text(
            text = jenisKelamin,
            style = MaterialTheme.typography.bodyLarge,
            color = warnaError,
            fontSize = 20.sp
        )
    }
}