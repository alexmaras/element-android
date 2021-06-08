/*
 * Copyright 2019 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.vector.app.features.form

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import im.vector.app.R
import im.vector.app.core.epoxy.ClickListener
import im.vector.app.core.epoxy.VectorEpoxyHolder
import im.vector.app.core.epoxy.VectorEpoxyModel
import im.vector.app.core.epoxy.onClick
import im.vector.app.features.themes.ThemeUtils

@EpoxyModelClass(layout = R.layout.item_form_advanced_toggle)
abstract class FormAdvancedToggleItem : VectorEpoxyModel<FormAdvancedToggleItem.Holder>() {

    @EpoxyAttribute lateinit var title: CharSequence
    @EpoxyAttribute var expanded: Boolean = false
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash) var listener: ClickListener? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        val tintColor = ThemeUtils.getColor(holder.view.context, R.attr.riotx_text_secondary)
        val expandedArrowDrawableRes = if (expanded) R.drawable.ic_expand_more_white else R.drawable.ic_expand_less_white
        val expandedArrowDrawable = ContextCompat.getDrawable(holder.view.context, expandedArrowDrawableRes)?.also {
            DrawableCompat.setTint(it, tintColor)
        }
        holder.titleView.setCompoundDrawablesWithIntrinsicBounds(null, null, expandedArrowDrawable, null)
        holder.titleView.text = title
        holder.view.onClick(listener)
    }

    class Holder : VectorEpoxyHolder() {
        val titleView by bind<TextView>(R.id.itemFormAdvancedToggleTitleView)
    }
}
