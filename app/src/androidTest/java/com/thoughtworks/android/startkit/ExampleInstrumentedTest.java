package com.thoughtworks.android.startkit;

import com.thoughtworks.android.startkit.dagger.DaggerAndroidTestAppComponent;
import com.thoughtworks.android.startkit.dagger.module.ApplicationModule;
import com.thoughtworks.android.startkit.dagger.module.TestLocalDatabaseModule;
import com.thoughtworks.android.startkit.douban.movie.view.MovieItemListFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Before
    public void useAppContext() {
        StartkitApplication applicationContext = (StartkitApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        DaggerAndroidTestAppComponent.builder()
                .applicationModule(new ApplicationModule(applicationContext))
                .testLocalDatabaseModule(new TestLocalDatabaseModule(applicationContext))
                .build()
                .inject(applicationContext);

    }

    @Test
    public void IntentTest_clickOnFirstMovie() {

        Intents.init();

        FragmentScenario<MovieItemListFragment> movieItemListFragmentFragmentScenario =
                FragmentScenario.launchInContainer(MovieItemListFragment.class);
        onView(withContentDescription(R.string.movie_list)).perform(click());

        intended(hasComponent(BookDetailActivity.class.getName()));

        Intents.release();
    }
}
