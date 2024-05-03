import React from 'react'
import { FaPlus } from "react-icons/fa6";
import styled from 'styled-components';

const InsertContainer = styled.div`
    width: 100%;
    padding-bottom: 24px;
    display: inline-flex;
    justify-content: center;
`

const InsertInput = styled.input`
    min-width: 250px;
    height: 40px;
    padding: 6px 12px;
    border-radius: 50px 0px 0px 50px;
    border: none;
    background-color: #f4f4f4;
    outline: none;
`

const InsertBtn = styled.button`
    width: 40px;
    height: 40px;
    border: none;
    cursor: pointer;
    border-radius: 0px 50px 50px 0px;
`

const TodoInsert = () => {
  return (
    <InsertContainer>
        <InsertInput 
            placeholder='할일 입력'/>
        <InsertBtn>
            <FaPlus/>
        </InsertBtn>
    </InsertContainer>
  )
}

export default TodoInsert