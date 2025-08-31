import pytest

from Python.List.dynamic_array import DynamicArray


#REQ-REM-001, REQ-REM-004, REQ-REM-012
def test_remove_at_returns_removed_element():
    arr = DynamicArray()
    arr.add(10)
    arr.add(20)
    assert arr.remove_at(0) == 10
    assert arr.remove_at(0) == 20

#REQ-REM-002
def test_remove_at_shifts_elements():
    arr = DynamicArray()
    for i in range(5):
        arr.add(i)
    arr.remove_at(2)  # Remove 2
    assert arr.get(0) == 0
    assert arr.get(1) == 1
    assert arr.get(2) == 3
    assert arr.get(3) == 4

#REQ-REM-003
def test_remove_at_decreases_size():
    arr = DynamicArray()
    arr.add(1)
    arr.add(2)
    arr.remove_at(0)
    assert arr.size() == 1
    arr.remove_at(0)
    assert arr.size() == 0

#REQ-REM-005, REQ-REM-006, REQ-REM-010
def test_remove_at_out_of_bounds():
    arr = DynamicArray()
    arr.add(1)
    with pytest.raises(ValueError, match="Index out of bounds"):
        arr.remove_at(1)
    with pytest.raises(ValueError, match="Index out of bounds"):
        arr.remove_at(-1)
    
    empty_arr = DynamicArray()
    with pytest.raises(ValueError, match="Index out of bounds"):
        empty_arr.remove_at(0)

#REQ-REM-012
def test_remove_at_first_element():
    arr = DynamicArray()
    arr.add(1)
    arr.add(2)
    arr.add(3)
    assert arr.remove_at(0) == 1
    assert arr.size() == 2
    assert arr.get(0) == 2
    assert arr.get(1) == 3

#REQ-REM-012
def test_remove_at_last_element():
    arr = DynamicArray()
    arr.add(1)
    arr.add(2)
    arr.add(3)
    assert arr.remove_at(2) == 3
    assert arr.size() == 2
    assert arr.get(0) == 1
    assert arr.get(1) == 2

#REQ-REM-012
def test_remove_at_middle_element():
    arr = DynamicArray()
    arr.add(1)
    arr.add(2)
    arr.add(3)
    arr.add(4)
    assert arr.remove_at(1) == 2
    assert arr.size() == 3
    assert arr.get(0) == 1
    assert arr.get(1) == 3
    assert arr.get(2) == 4
