package ru.yandex.practicum.contacts.presentation.country

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.yandex.practicum.contacts.data.models.CountryCode
import ru.yandex.practicum.contacts.presentation.ui.components.CommonBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCodeBottomSheet(
    selectedCodes: Set<CountryCode>,
    onCodesSelected: (Set<CountryCode>) -> Unit,
    onDismiss: () -> Unit
) {
    CommonBottomSheet(
        items = CountryCode.COMMON_CODES,
        selectedItems = selectedCodes,
        onItemsSelected = onCodesSelected,
        onDismiss = onDismiss,
        title = "Filter by country code",
        itemContent = { countryCode, isSelected ->
            CountryCodeItem(
                countryCode = countryCode,
                isSelected = isSelected,
                onCheckedChange = { checked ->
                    val newSelection = selectedCodes.toMutableSet()
                    if (checked) {
                        newSelection.add(countryCode)
                    } else {
                        newSelection.remove(countryCode)
                    }
                    onCodesSelected(newSelection)
                }
            )
        }
    )
}

@Composable
fun CountryCodeItem(
    countryCode: CountryCode,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit  // ИСПРАВЛЕНО: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = countryCode.code)
            Text(
                text = countryCode.country,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}