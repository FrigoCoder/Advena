package trace

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.closeTo
import org.hamcrest.Matchers.equalTo

fun assertFilterEquals(actual: Filter, expected: Filter, granularity: Double) {
    assertThat(actual.left, equalTo(expected.left))
    assertThat(actual.right, equalTo(expected.right))
    var point = actual.left - granularity
    while (point <= actual.right + granularity) {
        assertThat(actual(point), closeTo(expected(point), 1e-15))
        point += granularity
    }
}
