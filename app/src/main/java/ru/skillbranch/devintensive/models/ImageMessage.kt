package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isComing: Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, chat, isComing, date) {
    override fun formatMessage() =
        "id: $id from: ${from?.firstName} ${if (isIncoming) "Получил" else "Отправил"} изображение \"$image\" ${date.humanizeDiff()}"
}