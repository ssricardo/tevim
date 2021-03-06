package org.rss.tools.tevim.render

/**
 * Indicates a component that can be rendered.
 * Used to decorate original elements
 * @author ricardo
 */
interface Rendered {

    fun accept(visitor: DocumentVisitor)
}
