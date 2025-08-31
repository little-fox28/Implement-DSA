import pytest
from List.dynamic_array import DynamicArray

class TestIsContains:
    @pytest.fixture
    def array(self):
        # Setup a dynamic array with initial values as per example
        arr = DynamicArray()
        test_values = [10, 20, 30, 40, 50]
        for value in test_values:
            arr.add(value)
        return arr

    def test_contains_existing_value(self, array):
        """Test if is_contains returns True for existing value (REQ-CON-001, REQ-CON-002)"""
        assert array.is_contains(30) is True

    def test_contains_non_existing_value(self, array):
        """Test if is_contains returns False for non-existing value (REQ-CON-002)"""
        assert array.is_contains(99) is False

    # def test_contains_with_none_value(self, array):
    #     """Test if is_contains handles None values correctly"""
    #     array.add(None)
    #     assert array.is_contains(None) is True

    def test_contains_empty_array(self):
        """Test if is_contains works correctly with empty array"""
        empty_array = DynamicArray()
        assert empty_array.is_contains(10) is False

    def test_array_not_modified(self, array):
        """Test if is_contains does not modify the array (REQ-CON-003)"""
        initial_size = array.size()
        initial_elements = [array.get(i) for i in range(array.size())]

        array.is_contains(30)

        assert array.size() == initial_size
        assert all(array.get(i) == initial_elements[i] for i in range(array.size()))

    def test_performance_large_array(self):
        """Test performance with large array (REQ-CON-004)"""
        large_array = DynamicArray()
        test_size = 1_000_000

        # Fill array with numbers
        for i in range(test_size):
            large_array.add(i)

        # Test search for last element to force worst-case scenario
        result = large_array.is_contains(test_size - 1)
        assert result is True