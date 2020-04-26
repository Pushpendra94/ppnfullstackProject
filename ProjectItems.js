import React, { Component } from 'react'
import {Link } from "react-router-dom"
import PropTypes from "prop-types"
import {connect} from "react-redux"
import {deleteProject} from "../../actions/projectActions"
 class ProjectItems extends Component {
    onDeleteClick=id=>{
        this.props.deleteProject(id);
        };
    render() {
    
        const {project}=this.props;
        // this.state={
        //     projectName:this.project.projectName,
        //     projectIdentifir:this.project.projectIdentifir,
        //     description:this.description
        // }
        console.log(project);
    
        return (

            <div className="container">
            <div className="card card-body bg-light mb-3">
                <div className="row">
                    <div className="col-2">
        <span className="mx-auto">{project.projectIdentifir}</span>
                    </div>
                    <div className="col-lg-6 col-md-4 col-8">
                        <h3>{project.projectName}</h3>
                        <p>{project.description}</p>
                        {/* console.log("hello");
                        
                         <h3>           ( console.log(project)</h3> */}
                        {/* <h3>{project.projectName}</h3> */}
                    </div>
                    <div className="col-md-4 d-none d-lg-block">
                        <ul className="list-group">
                            <Link to={`/projectBoard/${project.projectIdentifir}`}>
                                <li className="list-group-item board">
                                    <i className="fa fa-flag-checkered pr-1">Project Board </i>
                                </li>
                            </Link>
                            <Link to={`/updateProject/${project.projectIdentifir}`}>
                                <li className="list-group-item update">
                                    <i className="fa fa-edit pr-1">Update Project Info</i>
                                    </li>
                                </Link>
                            
                            
                                <li className="list-group-item delete" 
                                  onClick={this.onDeleteClick.bind(this,project.projectIdentifir)
                                }>
                                    <i className="fa fa-minus-circle pr-1">Delete Project</i>
                                </li>
                            
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        )
    }
}
ProjectItems.propTypes={
    deleteProject:PropTypes.func.isRequired

};
export default  connect (null,{deleteProject})(ProjectItems);