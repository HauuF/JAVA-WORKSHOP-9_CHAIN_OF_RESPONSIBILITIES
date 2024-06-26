package com.javeriana.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Test Robot class behavior")
public class RobotBehaviorTest {

    Robot robot;

    @BeforeEach
    public void setUp() {
        try {
            // Obtain the constructor of Robot class
            Constructor<Robot> constructor = Robot.class.getDeclaredConstructor(String.class, double.class);

            // Create a new instance of Robot
            Robot robotInstance = constructor.newInstance("R2D2", 100.0);

            // Spy the created instance
            robot = Mockito.spy(robotInstance);
        } catch (NoSuchMethodException e) {
            fail("Constructor with String and double parameters does not exist in Robot class.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail("An error occurred while creating a new instance of Robot class.");
        }
    }

    @AfterEach
    public void tearDown() {
        robot = null;
    }

    @Test
    @DisplayName ("Robot is adding a component when it does not exceed maximum weight")
    @Order(8)
    public void testRobotIsAddingComponent() {
        try {
            // Obtain and invoke addComponent method
            Method addComponentMethod =
                Robot.class.getMethod("addComponent", int.class, String.class, double.class);
            boolean result = (boolean) addComponentMethod.invoke(robot, 1, "Component1", 10.0);

            Method getComponents = Robot.class.getMethod("getComponents");
            List<Component> components = (List<Component>) getComponents.invoke(robot);

            assertAll(
                () -> assertTrue(result,
                    "Robot is not adding a component when it not exceeds maximum weight. Maximum weight is 100.0 and the component weight added is 10.0. It is expected to add the component."),
                () -> assertEquals(1, components.size(),
                    "Robot is not adding a component when it not exceeds maximum weight. Maximum weight is 100.0 and the component weight added is 10.0. It is expected to add the component so list size must me 1.")

            );

        } catch (NoSuchMethodException e) {
            fail("Method addComponent does not exist in Robot class.");
        } catch (InvocationTargetException e) {
            fail("An error occurred while invoking addComponent method. Check its implementation.");
        } catch (IllegalAccessException e) {
            fail("Method addComponent is not accessible. Check its access modifier.");
        }
    }

    @Test
    @DisplayName ("Robot is not adding a component when it exceeds maximum weight")
    @Order(9)
    public void testRobotIsNotAddingComponentWhenItExceedsMaximumWeight() {
        try {
            // Obtain and invoke addComponent method
            Method addComponentMethod =
                Robot.class.getMethod("addComponent", int.class, String.class, double.class);
            boolean result = (boolean) addComponentMethod.invoke(robot, 1, "Component1", 101.0);

            Method getComponents = Robot.class.getMethod("getComponents");
            List<Component> components = (List<Component>) getComponents.invoke(robot);


            assertAll(
                () ->   assertFalse(result,
                    "Robot is adding a component when it exceeds maximum weight. Maximum weight is 100.0 and the component weight added is 101.0. It is expected to not add the component."),
                () -> assertEquals(0, components.size(),
                    "Robot is adding a component when it exceeds maximum weight. Maximum weight is 100.0 and the component weight added is 101.0. It is expected to not add the component so list size must me 0.")

            );

        } catch (NoSuchMethodException e) {
            fail("Method addComponent does not exist in Robot class.");
        } catch (InvocationTargetException e) {
            fail("An error occurred while invoking addComponent method. Check its implementation.");
        } catch (IllegalAccessException e) {
            fail("Method addComponent is not accessible. Check its access modifier.");
        }
    }

    @Test
    @DisplayName ("Robot is getting the total weight of its components")
    @Order(10)
    public void testRobotIsGettingComponentsWeight() {
        try {
            // Obtain and invoke addComponent and getComponentsWeight methods
            Method addComponentMethod =
                Robot.class.getMethod("addComponent", int.class, String.class, double.class);
            Method getComponentsWeightMethod = Robot.class.getMethod("getComponentsWeight");

            addComponentMethod.invoke(robot, 1, "Component1", 10.0);
            addComponentMethod.invoke(robot, 2, "Component2", 20.0);
            addComponentMethod.invoke(robot, 3, "Component3", 30.0);

            double totalWeight = (double) getComponentsWeightMethod.invoke(robot);

            assertEquals(60, totalWeight,
                "Robot is not getting the correct total weight of its components. Weight of components added is 10.0, 20.0 and 30.0. It is expected to have a total weight of 60.0.");
        } catch (NoSuchMethodException e) {
            fail("A method does not exist in Robot class.");
        } catch (InvocationTargetException e) {
            fail("An error occurred while invoking a method. Check its implementation.");
        } catch (IllegalAccessException e) {
            fail("A method is not accessible. Check its access modifier.");
        }
    }

    @Test
    @DisplayName ("Robot is getting the names of its components")
    @Order(11)
    public void testRobotIsGettingComponentsNames() {
        try {
            // Obtain and invoke addComponent and getComponentsNames methods
            Method addComponentMethod =
                Robot.class.getMethod("addComponent", int.class, String.class, double.class);
            Method getComponentsNamesMethod = Robot.class.getMethod("getComponentsNames");

            addComponentMethod.invoke(robot, 1, "Component1", 10.0);
            addComponentMethod.invoke(robot, 2, "Component2", 20.0);
            addComponentMethod.invoke(robot, 2, "Component2", 20.0);
            addComponentMethod.invoke(robot, 3, "Component3", 30.0);

            List<String> componentsNames = (List<String>) getComponentsNamesMethod.invoke(robot);
            List<String> expectedComponentsNames = List.of("Component1", "Component2", "Component3");
            System.out.println("Testing the following components names: " + componentsNames);
            assertAll(
                () -> assertEquals(3, componentsNames.size(),
                    "Robot is not getting the correct number of components names. Check for duplicates. The components names added are Component1, Component2, Component2 and Component3." +
                        "It is expected to have only three components names."),
                () -> assertEquals(expectedComponentsNames, componentsNames,
                    "Robot is not getting the correct components names. Check for duplicates. The components names added are Component1, Component2, Component2 and Component3."
                        + "It is expected to have only three components names (Component1,Component2 and Component3).")
            );
        } catch (NoSuchMethodException e) {
            fail("A method does not exist in Robot class.");
        } catch (InvocationTargetException e) {
            fail("An error occurred while invoking a method. Check its implementation.");
        } catch (IllegalAccessException e) {
            fail("A method is not accessible. Check its access modifier.");
        }
    }
}
