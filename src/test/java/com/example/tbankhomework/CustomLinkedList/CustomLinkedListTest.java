package com.example.tbankhomework.CustomLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.tbankhomework.Exception.CustomLinkedListException;

import java.util.Arrays;
import java.util.List;

class CustomLinkedListTest {

    private CustomLinkedList<Integer> customLinkedList;

    @BeforeEach
    void setUp() {
        customLinkedList = new CustomLinkedList<>();
    }

    @Test
    @DisplayName("add() should correctly add elements")
    void testAdd_shouldAddElements() throws CustomLinkedListException {
        customLinkedList.add(1);
        customLinkedList.add(2);

        assertEquals(1, customLinkedList.get(0));
        assertEquals(2, customLinkedList.get(1));
    }

    @Test
    @DisplayName("get() should return element at the given index")
    void testGet_shouldReturnElement_whenValidIndex() throws CustomLinkedListException {
        customLinkedList.add(1);
        customLinkedList.add(2);

        assertEquals(1, customLinkedList.get(0));
        assertEquals(2, customLinkedList.get(1));
    }

    @Test
    @DisplayName("size() should return correct size")
    void testSize_shouldReturnCorrectSize() {
        assertEquals(0, customLinkedList.size());

        customLinkedList.add(1);
        assertEquals(1, customLinkedList.size());
    }

    @Test
    @DisplayName("remove() should remove element at the given index")
    void testRemove_shouldRemoveElement_whenValidIndex() throws CustomLinkedListException {
        customLinkedList.add(1);
        customLinkedList.add(2);
        customLinkedList.add(3);

        customLinkedList.remove(1);

        assertEquals(2, customLinkedList.size());
        assertEquals(3, customLinkedList.get(1));
    }

    @Test
    @DisplayName("contains() should return true when element exists")
    void testContains_shouldReturnTrue_whenElementExists() {
        customLinkedList.add(5);
        assertTrue(customLinkedList.contains(5));
    }

    @Test
    @DisplayName("contains() should return false when element does not exist")
    void testContains_shouldReturnFalse_whenElementDoesNotExist() {
        customLinkedList.add(5);
        assertFalse(customLinkedList.contains(10));
    }

}
