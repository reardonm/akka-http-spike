package com.mtnsat.ir.test_util;

import com.google.common.base.Objects;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

/**
 Hamcrest matchers for Optional values based on https://github.com/gkonst/hamcrest-extras#contents
 */
public class OptionalMatcher<T> extends TypeSafeMatcher<Optional<? extends T>> {

    public static OptionalMatcher<Object> isEmpty() {
        return new OptionalMatcher<Object>(false);
    }

    public static OptionalMatcher<Object> isPresent() {
        return new OptionalMatcher<Object>(true);
    }

    public static <T> OptionalMatcher<T> isValue(T value) {
        return new OptionalMatcher<T>(value);
    }

    public static <T> OptionalMatcher<T> isValue(Matcher<T> matcher) {
        return new OptionalMatcher<T>(matcher);
    }

    private final boolean expectValue;

    private final Optional<T> expected;
    private final Optional<Matcher<T>> matcher;

    private OptionalMatcher(boolean expectValue) {
        this.expectValue = expectValue;
        this.expected = Optional.empty();
        this.matcher = Optional.empty();
    }

    private OptionalMatcher(T value) {
        if (value == null) throw new NullPointerException();
        this.expectValue = true;
        this.expected = Optional.of(value);
        this.matcher = Optional.empty();
    }

    private OptionalMatcher(Matcher<T> matcher) {
        this.expectValue = true;
        this.expected = Optional.empty();
        this.matcher = Optional.of(matcher);
    }

    @Override
    public void describeTo(Description description) {
        if (!expectValue) {
            description.appendText("<Empty>");
        } else if (expected.isPresent()) {
            description.appendValue(expected);
        } else if (matcher.isPresent()) {
            description.appendText("an Optional value matching ");
            matcher.get().describeTo(description);
        } else {
            description.appendText("<Present>");
        }
    }

    @Override
    public boolean matchesSafely(Optional<? extends T> item) {
        if (!expectValue) {
            return !item.isPresent();
        } else if (expected.isPresent()) {
            return item.isPresent() && Objects.equal(item.get(), expected.get());
        } else if (matcher.isPresent()) {
            return item.isPresent() && matcher.get().matches(item.get());
        } else {
            return item.isPresent();
        }
    }
}