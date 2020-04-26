import React, { Component } from 'react'
import ProjectItems from'./Project/ProjectItems';
import CreateProjectButton from './Project/CreateProjectButton';
import {connect } from "react-redux";
import {getProjects} from "../actions/projectActions";
import PropTypes from "prop-types"
 class DashBoard extends Component {

    componentDidMount(){
        this.props.getProjects();
    }
    render() {
        // const projectObject={
        //     projectName:"ProjectName Props",
        //     projectIdentifir:"Props",
        //     description:"description from prop"
        // }
        const {projects} =this.props.project
        return (
            <div className="projects">
        <div className="container">
            <div className="row">
                <div className="col-md-12">
                    <h1 className="display-4 text-center">Projects</h1>
                    <br />
                    <CreateProjectButton/>
                    <br />
                    <hr />
                    {projects.map(project=>(
                        <ProjectItems key={project.id} project={project}/>))}
                                       

                </div>
            </div>
        </div>
    </div>

    // <!-- End of Dashboard Component -->


        )
    }
}
DashBoard.propTypes={
    project:PropTypes.object.isRequired,
    getProjects:PropTypes.func.isRequired
};
const mapStateToProps=state=>({
    project:state.project
})
export default connect(mapStateToProps,{getProjects}) (DashBoard);