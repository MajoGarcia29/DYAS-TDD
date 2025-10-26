package edu.eci.cvds.tdd.registry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegistryTest {

    private Registry registry;

    @Before
    public void setUp() {
        registry = new Registry();
        registry.clearRegistry();
    }

    // 1 - Persona nula -> INVALID (BDD: Given null, When register, Then INVALID)
    @Test
    public void shouldReturnInvalidWhenPersonIsNull() {
        // Arrange
        Person p = null;
        // Act
        RegisterResult result = registry.registerVoter(p);
        // Assert
        Assert.assertEquals(RegisterResult.INVALID, result);
    }

    // 2 - Persona muerta -> DEAD
    @Test
    public void shouldRejectDeadPerson() {
        // Arrange
        Person dead = new Person("Carlos", 2, 40, Gender.MALE, false);
        // Act
        RegisterResult result = registry.registerVoter(dead);
        // Assert
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    // 3 - id inválido (<=0) -> INVALID
    @Test
    public void shouldRejectWhenIdIsZeroOrNegative() {
        // Arrange
        Person p0 = new Person("X", 0, 25, Gender.FEMALE, true);
        Person pNeg = new Person("Y", -5, 25, Gender.UNIDENTIFIED, true);
        // Act
        RegisterResult r0 = registry.registerVoter(p0);
        RegisterResult rNeg = registry.registerVoter(pNeg);
        // Assert
        Assert.assertEquals(RegisterResult.INVALID, r0);
        Assert.assertEquals(RegisterResult.INVALID, rNeg);
    }

    // 4 - edad inválida (negativa) -> INVALID_AGE
    @Test
    public void shouldRejectInvalidNegativeAge() {
        // Arrange
        Person p = new Person("Mario", 10, -1, Gender.MALE, true);
        // Act
        RegisterResult result = registry.registerVoter(p);
        // Assert
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    // 5 - edad inválida (> MAX) -> INVALID_AGE
    @Test
    public void shouldRejectAgeOverMax() {
        // Arrange
        Person p = new Person("Tito", 11, Registry.MAX_AGE + 1, Gender.MALE, true);
        // Act
        RegisterResult result = registry.registerVoter(p);
        // Assert
        Assert.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    // 6 - menor de 18 -> UNDERAGE (borde 17)
    @Test
    public void shouldRejectUnderageAt17() {
        // Arrange
        Person minor = new Person("Luisa", 3, 17, Gender.FEMALE, true);
        // Act
        RegisterResult result = registry.registerVoter(minor);
        // Assert
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    // 7 - adulto 18 -> VALID (borde inferior 18)
    @Test
    public void shouldAcceptAdultAt18() {
        // Arrange
        Person p = new Person("Ana", 4, 18, Gender.FEMALE, true);
        // Act
        RegisterResult result = registry.registerVoter(p);
        // Assert
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    // 8 - edad limite 120 -> VALID (borde superior)
    @Test
    public void shouldAcceptMaxAge120() {
        // Arrange
        Person p = new Person("Abuelo", 5, 120, Gender.MALE, true);
        // Act
        RegisterResult result = registry.registerVoter(p);
        // Assert
        Assert.assertEquals(RegisterResult.VALID, result);
    }

    // 9 - duplicado -> DUPLICATED
    @Test
    public void shouldDetectDuplicatedId() {
        // Arrange
        Person first = new Person("P1", 777, 30, Gender.UNIDENTIFIED, true);
        Person second = new Person("P2", 777, 45, Gender.UNIDENTIFIED, true);
        // Act
        RegisterResult r1 = registry.registerVoter(first);
        RegisterResult r2 = registry.registerVoter(second);
        // Assert
        Assert.assertEquals(RegisterResult.VALID, r1);
        Assert.assertEquals(RegisterResult.DUPLICATED, r2);
    }
}
