package com.mirf.core.common

import java.awt.Color
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener
import java.awt.image.BufferedImage
import java.util.concurrent.locks.ReentrantLock
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.border.EmptyBorder
import javax.swing.border.LineBorder


class ImagePicker(val images: List<BufferedImage>) : JPanel(), MouseWheelListener, MouseListener {

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()
    private val frame: JFrame
    private var currentImageIndex: Int = 0
    private var pickedImages: MutableSet<Int> = mutableSetOf()

    init {
        displayImg(0)

        JFrame.setDefaultLookAndFeelDecorated(true)
        frame = JFrame("Image picker")
        frame.defaultCloseOperation = JFrame.HIDE_ON_CLOSE

        frame.add(this)
        frame.addMouseListener(this)
        frame.addMouseWheelListener(this)
        frame.pack()
        frame.isVisible = true

    }

    override fun mouseClicked(e: MouseEvent?) {
        if (!pickedImages.contains(currentImageIndex)) {
            pickedImages.add(currentImageIndex)
        } else {
            pickedImages.remove(currentImageIndex)
        }
        displayImg(currentImageIndex)
    }

    override fun mouseWheelMoved(e: MouseWheelEvent?) {
        val steps = e!!.wheelRotation
        currentImageIndex += steps
        if (currentImageIndex < 0)
            currentImageIndex = 0

        if (currentImageIndex >= images.size)
            currentImageIndex = images.size - 1

        displayImg(currentImageIndex)
    }

    private fun displayImg(index: Int) {
        this.removeAll()
        this.updateUI()
        val label = JLabel(ImageIcon(images[index]))
        this.add(label)
        if (pickedImages.contains(index))
            this.border = LineBorder(Color.green, 5)
        else
            this.border = EmptyBorder(5, 5, 5, 5)
    }

    override fun mouseReleased(e: MouseEvent?) {}
    override fun mouseEntered(e: MouseEvent?) {}
    override fun mouseExited(e: MouseEvent?) {}
    override fun mousePressed(e: MouseEvent?) {}


    fun getPickedImages(): List<BufferedImage> {
        while (frame.isVisible)
            Thread.sleep(1000)
        return images.slice(pickedImages)
    }

}