package com.example.android.architecture.blueprints.todoapp.data.source

import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DefaultTasksRepositoryTest {
    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }

    private lateinit var tasksRemoteDataSource: FakeDataSource
    private lateinit var tasksLocalDataSource: FakeDataSource

    // Class under test
    private lateinit var tasksRepository: DefaultTasksRepository

    @Before
    fun createRepository() {
        tasksRemoteDataSource = FakeDataSource(remoteTasks.toMutableList())
        tasksLocalDataSource = FakeDataSource(localTasks.toMutableList())
        // Get a reference to the class under test
        tasksRepository = DefaultTasksRepository(
                // TODO Dispatchers.Unconfined should be replaced with Dispatchers.Main
                //  this requires understanding more about coroutines + testing
                //  so we will keep this as Unconfined for now.
                tasksRemoteDataSource, tasksLocalDataSource, Dispatchers.Unconfined
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    /*
    * runBlockingTest: makes sure the code is run synchronously and immediately
    * makes coroutines run like non-coroutines. Only for testing.
    * should be used only in test classes (never in main source set or in test doubles!)
    */
    fun getTasks_requestsAllTasksFromRemoteDataSource() = runBlockingTest {
        val tasks = tasksRepository.getTasks(forceUpdate = true) as Result.Success

        assertThat(tasks.data, equalTo(remoteTasks))

    }
}
