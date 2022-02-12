import React, { Component } from "react";
import { usState } from "./UsStates";

class UserForm extends Component {
  state = {};
  render() {
    return (
      <div className="container">
        User profile
        <form>
          <div className="form-group">
            <label>Fisrt Name</label>
            <input id="username" type="text" className="form-control" />
          </div>
          <div className="form-group">
            <label>Middle Name</label>
            <input id="username" type="text" className="form-control" />
          </div>
          <div className="form-group">
            <label>Last Name</label>
            <input id="username" type="text" className="form-control" />
          </div>

          <input type="radio" name="gender" value="male"></input>
          <label for="male">Male</label>
          <br></br>
          <input type="radio" name="gender" value="female"></input>
          <label for="male">Female</label>

          <div className="form-group">
            <label>Address</label>
            <input id="address" type="text" className="form-control" />
          </div>

          <div className="form-row">
            <div className="form-group col-md-6">
              <label for="inputCity">City</label>
              <input type="text" className="form-control" id="inputCity" />
            </div>

            <div className="form-group col-md-4">
              <label for="">State</label>
              <select id="" classNames="form-control">
                <option selected>Choose...</option>;
                {usState.map((e) => {
                  return <option>{e}</option>;
                })}
              </select>
            </div>

            <div classNames="form-group col-md-2">
              <label for="inputZip">Zip</label>
              <input type="text" classNames="form-control" id="" />
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="emailId">Email Id</label>
            <input id="emailId" type="text" className="form-control" />
          </div>

          <div className="form-group col-md-4">
            <label for="">Role</label>
            <select id="" classNames="form-control">
              <option selected>Choose...</option>
              <option>Admin</option>
              <option>HR</option>
              <option>Student</option>
              <option>User</option>
              <option>Instructor</option>
            </select>
          </div>

          <button className="btn btn-success">Update</button>
        </form>
      </div>
    );
  }
}

export default UserForm;
