package com.isj.gestionmateriel.webapp.calcul;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatorTest {

    private static Instant startedAt;
    private Calculator calculatorUnderTest;

    @BeforeAll
    static public void initStartingTime() {
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    static public void showTestDuration() {
        System.out.println("Appel après tous les tests");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @BeforeEach
    public void initCalculator() {
        System.out.println("Appel avant chaque test");
        calculatorUnderTest = new Calculator();
    }

    @AfterEach
    public void undefCalculator() {
        System.out.println("Appel après chaque test");
        calculatorUnderTest = null;
    }

    @Test
    void testAddToPositiveNumber(){
       //ARRANGE or GIVEN
        int a = 5;
        int b = 6;

        // ACT or WHEN
        int result = calculatorUnderTest.add(a,b);

        // ASSERT or THEN
       // assertEquals(11,result); JUnit 5
        assertThat(result).isEqualTo(11); // AssertJ

    }

    @Test
    public void multiply_shouldReturnTheProduct_ofTwoIntegers() {
        // Arrange
        int a = 42;
        int b = 11;

        // Act
        int produit = calculatorUnderTest.multiply(a, b);

        // Assert
       // assertEquals(462, produit);
        assertThat(produit).isEqualTo(462);
    }

    @ParameterizedTest(name = "{0} x 0 doit être égal à 0")
    @ValueSource(ints = { 1, 2, 42, 1011, 5089 })
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
        // Arrange -- Tout est prêt !

        // Act -- Multiplier par zéro
        int actualResult = calculatorUnderTest.multiply(arg, 0);

        // Assert -- ça vaut toujours zéro !
        //assertEquals(0, actualResult);
        assertThat(actualResult).isEqualTo(0);
    }

    @ParameterizedTest(name = "{0} + {1} should equal to {2}")
    @CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
    public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
        // Arrange -- Tout est prêt !

        // Act
        int actualResult = calculatorUnderTest.add(arg1, arg2);

        // Assert
       // assertEquals(expectResult, actualResult);
        assertThat(expectResult).isEqualTo(actualResult);
    }

    @Timeout(1)
    @Test
    public void longCalcul_shouldComputeInLessThan1Second() {
        // Arrange

        // Act
        calculatorUnderTest.longCalculation();

        // Assert
        // ...
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger() {
        // GIVEN
        int number = 95897;

        // WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(9, 5, 8, 7);
        Set<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toSet());
        assertEquals(expectedDigits, actualDigits);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger() {
       //GIVEN
        int number = -124432;
        //WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        //THEN
        assertThat(actualDigits).containsExactlyInAnyOrder(1, 2, 3, 4);
    }

    @Test
    public void listDigits_shouldReturnsTheListOfZero_ofZero() {
        //GIVEN
        int number = 0;
        //WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        //THEN
        assertThat(actualDigits).containsExactly(0);
    }

}
