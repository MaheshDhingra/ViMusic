package it.vfsfitvnm.vimusic.ui.screens.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import it.vfsfitvnm.vimusic.LocalPlayerAwarePaddingValues
import it.vfsfitvnm.vimusic.enums.ColorPaletteMode
import it.vfsfitvnm.vimusic.enums.ColorPaletteName
import it.vfsfitvnm.vimusic.enums.ThumbnailRoundness
import it.vfsfitvnm.vimusic.ui.components.themed.Header
import it.vfsfitvnm.vimusic.ui.screens.EnumValueSelectorSettingsEntry
import it.vfsfitvnm.vimusic.ui.screens.SettingsEntryGroupText
import it.vfsfitvnm.vimusic.ui.screens.SettingsGroupSpacer
import it.vfsfitvnm.vimusic.ui.screens.SwitchSettingEntry
import it.vfsfitvnm.vimusic.ui.styling.LocalAppearance
import it.vfsfitvnm.vimusic.utils.colorPaletteModeKey
import it.vfsfitvnm.vimusic.utils.colorPaletteNameKey
import it.vfsfitvnm.vimusic.utils.isShowingThumbnailInLockscreenKey
import it.vfsfitvnm.vimusic.utils.rememberPreference
import it.vfsfitvnm.vimusic.utils.thumbnailRoundnessKey

@ExperimentalAnimationApi
@Composable
fun AppearanceSettingsTab() {
    val (colorPalette) = LocalAppearance.current

    var colorPaletteName by rememberPreference(colorPaletteNameKey, ColorPaletteName.Dynamic)
    var colorPaletteMode by rememberPreference(colorPaletteModeKey, ColorPaletteMode.System)
    var thumbnailRoundness by rememberPreference(
        thumbnailRoundnessKey,
        ThumbnailRoundness.Light
    )
    var isShowingThumbnailInLockscreen by rememberPreference(
        isShowingThumbnailInLockscreenKey,
        false
    )

    Column(
        modifier = Modifier
            .background(colorPalette.background0)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(LocalPlayerAwarePaddingValues.current)
    ) {
        Header(title = "Appearance")

        SettingsEntryGroupText(title = "COLORS")

        EnumValueSelectorSettingsEntry(
            title = "Theme",
            selectedValue = colorPaletteName,
            onValueSelected = { colorPaletteName = it }
        )

        EnumValueSelectorSettingsEntry(
            title = "Theme mode",
            selectedValue = colorPaletteMode,
            isEnabled = colorPaletteName != ColorPaletteName.PureBlack,
            onValueSelected = { colorPaletteMode = it }
        )

        SettingsGroupSpacer()

        SettingsEntryGroupText(title = "SHAPES")

        EnumValueSelectorSettingsEntry(
            title = "Thumbnail roundness",
            selectedValue = thumbnailRoundness,
            onValueSelected = { thumbnailRoundness = it }
        )

        SettingsGroupSpacer()

        SettingsEntryGroupText(title = "LOCKSCREEN")

        SwitchSettingEntry(
            title = "Show song cover",
            text = "Use the playing song cover as the lockscreen wallpaper",
            isChecked = isShowingThumbnailInLockscreen,
            onCheckedChange = { isShowingThumbnailInLockscreen = it }
        )
    }
}
