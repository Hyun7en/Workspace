import React from 'react'
import styled from 'styled-components'
import { FaXmark } from "react-icons/fa6";

const ListContainer = styled.ul`
    width: 100%;
    margin: 12px 0px;
    padding: 0px 30px;
`

const TodoRow = styled.li`
    height: 34px;
    background: #f4f4f4;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    cursor: pointer;
`

const RomoveBtn = styled.button`
    cursor: pointer;
    width: 34px;
    height: 34px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    position: absolute;
    right: 0px;
    background-color: rgb(192, 226, 255);
    border: none;
`

const TodoListView = ({todos}) => {

    return (
        <ListContainer>
            {
                todos.map(todo => 
                        <TodoRow>
                            {todo.title}
                            <RomoveBtn>
                                <FaXmark/>
                            </RomoveBtn>
                        </TodoRow>)
            }
        </ListContainer>
    )
}

export default TodoListView