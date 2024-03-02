package the_null_pointer.secure_device.ui.search.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import the_null_pointer.secure_device.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    searchHints: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    DockedSearchBar(
        query = text,
        onQueryChange = {
            text = it
            onQueryChange(it)
        },
        placeholder = { Text(stringResource(R.string.search)) },
        trailingIcon = {
            IconButton(onClick = {
                active = false
                onSearch(text)
            }) {
                Icon(Icons.Filled.Search, contentDescription = null)
            }
        },
        modifier = modifier.heightIn(50.dp, 200.dp),
        active = active,
        shape = TextFieldDefaults.shape,
        onActiveChange = { active = it },
        onSearch = {
            onSearch(it)
            active = false
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(8.dp), verticalArrangement = spacedBy(4.dp)) {
            items(searchHints, key = { it }) { searchHint ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 48.dp)
                        .clickable {
                            text = searchHint.second
                            onSearch(searchHint.second)
                            active = false
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = searchHint.first
                    )
                }
            }
        }
    }
}