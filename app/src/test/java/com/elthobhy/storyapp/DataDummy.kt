package com.elthobhy.storyapp

import com.elthobhy.storyapp.core.domain.model.Story

object DataDummy {
    fun generateDummy(): List<Story> {
        val list = ArrayList<Story>()
        for (i in 0..10) {
            val listStory = Story(
                name = "Budi",
                createdAt = "2022-01-08T06:34:18.598Z",
                description = "Lorem Ipsum",
                id = "story-FvU4u0Vp2S3PMsFg",
                lat = -10.212,
                lon = -16.002,
                photoUrl = "https://story-api.dicoding.dev/images/stories/photos-1641623658595_dummy-pic.png"
            )
            list.add(listStory)
        }
        return list
    }
}