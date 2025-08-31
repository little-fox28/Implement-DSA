import pytest

from List.dynamic_array import DynamicArray


# This test suite is based on the requirements in Python/requirements/List/remove_element.json

# REQ-REM-002: remove_element(value) method to remove the first occurrence.
# Assumes a Pythonic implementation where the method returns None on success.
def test_remove_element_success_and_returns_none():
    """
    Tests successful removal of an element and that the method returns None.
    """
    arr = DynamicArray[str]()
    arr.add("A")
    arr.add("B")
    arr.add("C")
    
    return_value = arr.remove_element("B")
    
    assert return_value is None
    assert arr.size() == 2
    assert arr.get(0) == "A"
    assert arr.get(1) == "C"

# REQ-REM-002 / Acceptance Criteria: Removing by value deletes only the first occurrence.
def test_remove_element_only_first_occurrence():
    """
    Tests that remove_element(value) removes only the first occurrence of a duplicated element.
    """
    arr = DynamicArray[int]()
    arr.add(10)
    arr.add(20)
    arr.add(30)
    arr.add(20)
    arr.add(40)
    
    arr.remove_element(20)
    
    assert arr.size() == 4
    # The list should now be [10, 30, 20, 40]
    assert arr.get(0) == 10
    assert arr.get(1) == 30
    assert arr.get(2) == 20
    assert arr.get(3) == 40

# REQ-REM-005: Return 'Not Found' error if value does not exist.
# REQ-REM-009: Provide meaningful error messages such as 'Value not found'.
def test_remove_element_not_found_raises_value_error():
    """
    Tests that remove_element raises a ValueError for a non-existent element.
    """
    arr = DynamicArray[int]()
    arr.add(10)
    arr.add(20)
    
    # Test with a non-empty array
    with pytest.raises(ValueError, match="Value not found"):
        arr.remove_element(99)
        
    # Test with an empty array
    empty_arr = DynamicArray[int]()
    with pytest.raises(ValueError, match="Value not found"):
        empty_arr.remove_element(1)

def test_remove_element_from_extremes():
    """
    Tests removing the first and the last elements of the array.
    """
    arr = DynamicArray[int]()
    arr.add(10)
    arr.add(20)
    arr.add(30)

    # Remove last element
    arr.remove_element(30)
    assert arr.size() == 2
    assert arr.get(0) == 10
    assert arr.get(1) == 20

    # Remove first element
    arr.remove_element(10)
    assert arr.size() == 1
    assert arr.get(0) == 20

    # Remove remaining element
    arr.remove_element(20)
    assert arr.size() == 0
    assert arr.is_empty()
