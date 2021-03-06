package com.aldoapps.opmsclass.herolist.interactor

import com.aldoapps.opmsclass.hero.interactor.GetHeroList
import com.aldoapps.opmsclass.hero.interactor.GetHeroListCallback
import com.aldoapps.opmsclass.hero.repository.HeroDatabase
import com.aldoapps.opmsclass.hero.repository.HeroEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner

/**
 * Created by aldo on 02/02/18.
 */
@RunWith(RobolectricTestRunner::class)
class GetHeroListTest {

    private lateinit var heroDatabase: HeroDatabase

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockGetHeroListCallback: GetHeroListCallback<List<HeroEntity>>

    @Before
    fun setup() {
        heroDatabase = HeroDatabase
    }

    @Test
    fun shouldReturnList_whenGetHeroFromDb() {
        GetHeroList(heroDatabase, object : GetHeroListCallback<List<HeroEntity>> {
            override fun onFinished(heroList: List<HeroEntity>) {
                assertThat(heroList).isInstanceOf(List::class.java)
                assertThat(heroList).hasSize(13)
            }
        }).execute()
    }

    @Test
    fun shouldTriggerCallback_whenGetHeroFromDb() {
        // Given
        // When
        GetHeroList(heroDatabase, mockGetHeroListCallback).execute()

        // Then
        Mockito.verify(mockGetHeroListCallback).onFinished(anyList())
        Mockito.verifyNoMoreInteractions(mockGetHeroListCallback)
    }

}